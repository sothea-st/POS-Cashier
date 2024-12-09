package com.example.pos.system.feature.user_permission.role_has_permission.dto;

import java.util.List;

public record RoleHasRequest(
        List<RoleHasPermissionRequest> roleHasPermissionRequestList
) {
}
