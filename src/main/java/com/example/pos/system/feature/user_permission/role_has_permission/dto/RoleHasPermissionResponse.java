package com.example.pos.system.feature.user_permission.role_has_permission.dto;

import lombok.Builder;

@Builder
public record RoleHasPermissionResponse(
        Integer roleId,
        String roleName,
        Integer permissionId,
        String permissionName,
        Integer parentId,
        Boolean isVisible,
        Boolean isCreate,
        Boolean isView,
        Boolean isUpdate,
        Boolean isDelete

) {
}
