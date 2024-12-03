package com.example.pos.system.feature.user_permission.permission.dto;

import lombok.Builder;

@Builder
public record PermissionResponse(
        Integer id,
        String permissionName,
        Integer parentId
) {
}
