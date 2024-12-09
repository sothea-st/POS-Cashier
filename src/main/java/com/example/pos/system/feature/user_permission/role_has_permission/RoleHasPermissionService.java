package com.example.pos.system.feature.user_permission.role_has_permission;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.user_permission.role_has_permission.dto.RoleHasRequest;

public interface RoleHasPermissionService {

    ResponseSuccess create(RoleHasRequest roleHasRequest);

    JavaCollectionResponse<?> readByParentIdAndRoleId(Integer parentId,Integer roleId);

}
