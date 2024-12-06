package com.example.pos.system.feature.user_permission.permission;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.user_permission.permission.dto.PermissionRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/permissions")
public class PermissionController {
    // inject bean service
    private final PermissionService permissionService;

    @PostMapping
    public ResponseSuccess create(@Valid @RequestBody PermissionRequest permissionRequest){
        return permissionService.create(permissionRequest);
    }

    @GetMapping
    JavaCollectionResponse<?> read(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize
    ){
        System.out.println("pageSIze : dd " + pageSize);
        return permissionService.read(pageNumber, pageSize);
    }


    @GetMapping("/{id}")
    JavaResponse<?> readById(@PathVariable("id") Integer id){
        return permissionService.readById(id);
    }


    @GetMapping("/parentId/{parentId}")
    JavaCollectionResponse<?> readByParentId(@PathVariable("parentId") Integer parentId){
        return permissionService.readByParentId(parentId);
    }

    @DeleteMapping("/{id}")
    ResponseSuccess deleteById(@PathVariable("id") Integer id){
        return permissionService.deleteById(id);
    }


    @PutMapping("/{id}")
    ResponseSuccess updateById(@Valid @RequestBody PermissionRequest permissionRequest, @PathVariable("id") Integer id){
        return permissionService.updateById(permissionRequest,id);
    }


}
