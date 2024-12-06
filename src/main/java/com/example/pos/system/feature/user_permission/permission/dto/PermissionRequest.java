package com.example.pos.system.feature.user_permission.permission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PermissionRequest(
        @NotBlank(message = "The field permissionName is required!")
        String permissionName,
        @NotNull(message = "The field parentId is required!")
        Integer parentId,

        @NotNull(message = "The field createBy is required!")
        Integer createBy
) {
}
