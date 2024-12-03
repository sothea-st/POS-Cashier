package com.example.pos.system.feature.user_permission.permission;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.domain.role.permission.Permission;
import com.example.pos.system.domain.settings.Ranges;
import com.example.pos.system.feature.settings.range.dto.RangeResponse;
import com.example.pos.system.feature.user_permission.permission.dto.PermissionRequest;
import com.example.pos.system.feature.user_permission.permission.dto.PermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImp implements PermissionService{
    // inject bean repository
    private final PermissionRepository permissionRepository;

    @Override
    public ResponseSuccess create(PermissionRequest permissionRequest) {

        Permission permission = new Permission();
        permission.setCreateBy(permissionRequest.createBy());
        permission.setPermissionName(permissionRequest.permissionName());
        permission.setParentId(permissionRequest.parentId());
        permissionRepository.save(permission);
        return ResponseSuccess.builder().build();

    }

    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize) {
        long totalPageNumber = 0;
        List<PermissionResponse> data = new ArrayList<>();

        if(pageNumber == null && pageSize == null){
            // map value to List
            data = permissionRepository.findByStatusTrueAndIsDeletedFalse().stream()
                    .sorted(Comparator.comparing(Permission::getId).reversed())
                    .map(this::mapToPermissionResponse)
                    .toList();

            // assign total pages
            totalPageNumber = data.size();

        }else{
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            System.out.println("pageNumber : " + pageNumber + " pageSize : " + pageSize);
            // page request
            // pageNumber start from 1
            PageRequest pageRequest = PageRequest.of(pageNumber-1,pageSize,sortById);
            Page<Permission> pages = permissionRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

            // assign total pages
            totalPageNumber = pages.getTotalElements();

            // map value to List
            data = pages.getContent().stream()
                    .map(this::mapToPermissionResponse)
                    .toList();
        }

        return JavaCollectionResponse.builder()
                .count(totalPageNumber)
                .data(data)
                .build();
    }

    @Override
    public JavaCollectionResponse<?> readByParentId(Integer parentId) {

        if( !permissionRepository.existsByParentId(parentId) ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Permission not found with parentID : " + parentId);
        }


        List<PermissionResponse> data = permissionRepository.findByStatusTrueAndIsDeletedFalseAndParentId(parentId).stream().
                map(this::mapToPermissionResponse)
                .toList();

        return JavaCollectionResponse.builder()
                .count(data.size())
                .data(data)
                .build();
    }

    @Override
    public JavaResponse<?> readById(Integer id) {
        // validate permission id exist or not
        Permission permission = permissionRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Permission not found with id : " + id));

        return JavaResponse.builder()
                .data(mapToPermissionResponse(permission))
                .build();
    }

    @Override
    public ResponseSuccess deleteById(Integer id) {
        // validate permission id exist or not
        Permission permission = permissionRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Permission not found with id : " + id));
        permission.setStatus(false);
        permission.setDeleted(false);
        permissionRepository.save(permission);
        return ResponseSuccess.builder().build();
    }

    @Override
    public ResponseSuccess updateById(PermissionRequest permissionRequest, Integer id) {
        // validate permission id exist or not
        Permission permission = permissionRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Permission not found with id : " + id));

        permission.setPermissionName(permissionRequest.permissionName());
        permission.setParentId(permissionRequest.parentId());
        permission.setCreateBy(permissionRequest.createBy());
        permissionRepository.save(permission);
        return ResponseSuccess.builder().build();
    }

    private PermissionResponse mapToPermissionResponse(Permission permission){
            return  PermissionResponse.builder()
                    .id(permission.getId())
                    .permissionName(permission.getPermissionName())
                    .parentId(permission.getParentId())
                    .build();
    }
}
