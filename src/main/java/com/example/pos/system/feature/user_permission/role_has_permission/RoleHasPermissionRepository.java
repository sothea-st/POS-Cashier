package com.example.pos.system.feature.user_permission.role_has_permission;

import com.example.pos.system.domain.role.Role;
import com.example.pos.system.domain.role.role_have_permission.RoleHasPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleHasPermissionRepository extends JpaRepository<RoleHasPermission,Integer> {

        @Query("SELECT rhp FROM RoleHasPermission rhp WHERE rhp.parentId = :parentId AND rhp.role = :role")
        List<RoleHasPermission> findByParentIdAndRole(@Param("parentId") Integer parentId, @Param("role") Role role);

        Boolean existsByParentIdAndRole(Integer parentId,Role role);


}
