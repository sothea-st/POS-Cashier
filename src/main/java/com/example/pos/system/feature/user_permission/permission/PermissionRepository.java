package com.example.pos.system.feature.user_permission.permission;

import com.example.pos.system.domain.role.permission.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {


    Page<Permission> findByStatusTrueAndIsDeletedFalse(PageRequest pageRequest);


    List<Permission> findByStatusTrueAndIsDeletedFalse();


    Optional<Permission> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    Boolean existsByParentId(Integer parentId);
    List<Permission> findByStatusTrueAndIsDeletedFalseAndParentId(Integer parentId);



}
