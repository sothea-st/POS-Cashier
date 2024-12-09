package com.example.pos.system.feature.user_permission.role_has_permission;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response.JavaMessageResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.user_permission.role_has_permission.dto.RoleHasPermissionRequest;
import com.example.pos.system.feature.user_permission.role_has_permission.dto.RoleHasRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roleHasPermissions")
public class RoleHasPermissionController {
    // inject bean service
    private final RoleHasPermissionService roleHasPermissionService;

    @PostMapping
    ResponseSuccess create(@Valid @RequestBody RoleHasRequest roleHasRequest){
        return roleHasPermissionService.create(roleHasRequest);
    }

    @GetMapping
    public JavaCollectionResponse<?> readByParentIdAndRoleId(
            @RequestParam(name = "parentId") Integer parentId,
            @RequestParam(name = "roleId") Integer roleId
    ) {
        return roleHasPermissionService.readByParentIdAndRoleId(parentId,roleId);
    }


    @GetMapping("/readByRole")
    public JavaCollectionResponse<?> readByRoleId(
            @RequestParam(name = "roleId") Integer roleId
    ) {
        return roleHasPermissionService.readByRoleId(roleId);
    }

    @GetMapping("/{id}")
    public JavaMessageResponse<?> readById(
            @PathVariable(name = "id") Integer id
    ) {
        return roleHasPermissionService.readById(id);
    }


}
