package com.example.pos.system.feature.user_permission.permission;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.user_permission.permission.dto.PermissionRequest;

public interface PermissionService {
    ResponseSuccess create(PermissionRequest permissionRequest);

    JavaCollectionResponse<?> read(Integer pageNumber,Integer pageSize);

    JavaCollectionResponse<?> readByParentId(Integer parentId);

    JavaResponse<?> readById(Integer id);

    ResponseSuccess deleteById(Integer id);

    ResponseSuccess updateById(PermissionRequest permissionRequest, Integer id);



}
