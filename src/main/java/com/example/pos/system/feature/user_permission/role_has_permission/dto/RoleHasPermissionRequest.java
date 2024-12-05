package com.example.pos.system.feature.user_permission.role_has_permission.dto;

import jakarta.validation.constraints.NotNull;

public record RoleHasPermissionRequest(
        @NotNull(message = "The field permissionId is required!")
        Integer permissionId,

        @NotNull(message = "The field roleId is required!")
        Integer roleId,

        @NotNull(message = "The field parentId is required!")
        Integer parentId,

        Boolean isVisible,

        Boolean isCreate,

        Boolean isView,

        Boolean isUpdate,

        Boolean isDelete

) {
}
