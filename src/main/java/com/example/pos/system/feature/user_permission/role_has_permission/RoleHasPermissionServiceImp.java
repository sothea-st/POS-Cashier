package com.example.pos.system.feature.user_permission.role_has_permission;

import com.example.pos.system.constant.JavaResponse;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response.JavaMessageResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.domain.role.Role;
import com.example.pos.system.domain.role.permission.Permission;
import com.example.pos.system.domain.role.role_have_permission.RoleHasPermission;
import com.example.pos.system.feature.user_permission.permission.PermissionRepository;
import com.example.pos.system.feature.user_permission.role_has_permission.dto.RoleHasPermissionRequest;
import com.example.pos.system.feature.user_permission.role_has_permission.dto.RoleHasPermissionResponse;
import com.example.pos.system.feature.user_permission.role_has_permission.dto.RoleHasRequest;
import com.example.pos.system.layer.repository.roleAndPermissionRepository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleHasPermissionServiceImp implements RoleHasPermissionService {
    // inject bean repository
    private final RoleHasPermissionRepository roleHasPermissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public ResponseSuccess create(RoleHasRequest roleHasRequests) {

        List<RoleHasPermission> roleHasPermissions = new ArrayList<>();
        for (RoleHasPermissionRequest request : roleHasRequests.roleHasPermissionRequestList()) {
            Role role = roleRepository.findById(request.roleId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found with id : " + request.roleId()));

            Permission permission = permissionRepository.findByIdAndStatusTrueAndIsDeletedFalse(request.permissionId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found with id : " + request.permissionId()));


            // Delete all existing mappings for the role
            roleHasPermissionRepository.deleteAllByRole(role);


            // Create and prepare a new RoleHasPermission
            RoleHasPermission roleHasPermission = new RoleHasPermission();
            roleHasPermission.setParentId(request.parentId());
            roleHasPermission.setPermission(permission);
            roleHasPermission.setRole(role);
            roleHasPermission.setIsVisible(request.isVisible());
            roleHasPermission.setIsCreate(request.isCreate());
            roleHasPermission.setIsView(request.isView());
            roleHasPermission.setIsUpdate(request.isUpdate());
            roleHasPermission.setIsDelete(request.isDelete());
            roleHasPermissions.add(roleHasPermission);

        }

        // sale all
        roleHasPermissionRepository.saveAll(roleHasPermissions);


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

    @Override
    public JavaCollectionResponse<?> readByRoleId(Integer roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found with id : " + roleId));


        List<RoleHasPermissionResponse> data = roleHasPermissionRepository.findByRole(role).stream()
                .map(this::mapToRoleHasPermission)
                .toList();

        return JavaCollectionResponse.builder()
                .count(data.size())
                .data(data)
                .build();
    }

    @Override
    public JavaMessageResponse<?> readById(Integer id) {
//        RoleHasPermission roleHasPermission = roleHasPermissionRepository.findByPermissionAndRole(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RoleHasPermission not found with id : " + id));
//
//        var data = mapToRoleHasPermission(roleHasPermission);
//        return JavaMessageResponse.builder()
//                .data(data)
//                .build();
        return  null;

    }

    private RoleHasPermissionResponse mapToRoleHasPermission(RoleHasPermission roleHasPermission) {
        return RoleHasPermissionResponse.builder()
                .roleId(roleHasPermission.getRole().getId())
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
