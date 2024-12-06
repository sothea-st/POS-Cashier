package com.example.pos.system.feature.user_permission.role_has_permission;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.domain.role.Role;
import com.example.pos.system.domain.role.permission.Permission;
import com.example.pos.system.domain.role.role_have_permission.RoleHasPermission;
import com.example.pos.system.feature.user_permission.permission.PermissionRepository;
import com.example.pos.system.feature.user_permission.role_has_permission.dto.RoleHasPermissionRequest;
import com.example.pos.system.feature.user_permission.role_has_permission.dto.RoleHasPermissionResponse;
import com.example.pos.system.layer.repository.roleAndPermissionRepository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleHasPermissionServiceImp implements RoleHasPermissionService {
    // inject bean repository
    private final RoleHasPermissionRepository roleHasPermissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public ResponseSuccess create(RoleHasPermissionRequest roleHasPermissionRequest) {

        Role role = roleRepository.findById(roleHasPermissionRequest.roleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found with id : " + roleHasPermissionRequest.roleId()));

        Permission permission = permissionRepository.findByIdAndStatusTrueAndIsDeletedFalse(roleHasPermissionRequest.permissionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found with id : " + roleHasPermissionRequest.permissionId()));

        // parentId and role already exist
        if( roleHasPermissionRepository.existsByParentIdAndRole(roleHasPermissionRequest.parentId(), role) ) {
            return ResponseSuccess.builder().build();
        }

        RoleHasPermission roleHasPermission = new RoleHasPermission();
        roleHasPermission.setParentId(roleHasPermissionRequest.parentId());
        roleHasPermission.setPermission(permission);
        roleHasPermission.setRole(role);
        roleHasPermission.setIsVisible(roleHasPermissionRequest.isVisible());
        roleHasPermission.setIsCreate(roleHasPermissionRequest.isCreate());
        roleHasPermission.setIsView(roleHasPermissionRequest.isView());
        roleHasPermission.setIsUpdate(roleHasPermissionRequest.isUpdate());
        roleHasPermission.setIsDelete(roleHasPermissionRequest.isDelete());
        roleHasPermissionRepository.save(roleHasPermission);

        return ResponseSuccess.builder().build();
    }

    @Override
    public JavaCollectionResponse<?> readByParentIdAndRoleId(Integer parentId, Integer roleId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found with id : " + roleId));

//        permissionRepository.findByParentIdAndStatusTrueAndIsDeletedFalse(parentId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found with parentId : " + parentId));


        List<RoleHasPermissionResponse> data = roleHasPermissionRepository.findByParentIdAndRole(parentId, role)
                .stream()
                .map(this::mapToRoleHasPermission)
                .toList();


        return JavaCollectionResponse.builder()
                .data(data)
                .count(data.size())
                .build();
    }

    private RoleHasPermissionResponse mapToRoleHasPermission(RoleHasPermission roleHasPermission) {
        return RoleHasPermissionResponse.builder()
                .roleId(roleHasPermission.getId())
                .roleName(roleHasPermission.getRole().getRoleName())
                .permissionId(roleHasPermission.getPermission().getId())
                .permissionName(roleHasPermission.getPermission().getPermissionName())
                .isVisible(roleHasPermission.getIsVisible() == null ? false : roleHasPermission.getIsVisible())
                .isCreate(roleHasPermission.getIsCreate() == null ? false : roleHasPermission.getIsCreate())
                .isView(roleHasPermission.getIsView() == null ? false : roleHasPermission.getIsView())
                .isDelete(roleHasPermission.getIsDelete() == null ? false : roleHasPermission.getIsDelete())
                .isUpdate(roleHasPermission.getIsUpdate() == null ? false : roleHasPermission.getIsUpdate())
                .parentId(roleHasPermission.getParentId())
                .build();
    }
}
