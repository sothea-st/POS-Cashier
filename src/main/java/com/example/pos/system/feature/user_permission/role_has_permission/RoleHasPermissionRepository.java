package com.example.pos.system.feature.user_permission.role_has_permission;

import com.example.pos.system.domain.role.Role;
import com.example.pos.system.domain.role.permission.Permission;
import com.example.pos.system.domain.role.role_have_permission.RoleHasPermission;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleHasPermissionRepository extends JpaRepository<RoleHasPermission,Integer> {

        @Query("SELECT rhp FROM RoleHasPermission rhp WHERE rhp.parentId = :parentId AND rhp.role = :role")
        List<RoleHasPermission> findByParentIdAndRole(@Param("parentId") Integer parentId, @Param("role") Role role);

        Boolean existsByPermissionAndRole(Permission permission, Role role);

        // Custom method to delete all RoleHasPermission entries for a specific Role
        @Transactional
        @Modifying
        @Query("DELETE FROM RoleHasPermission rhp WHERE rhp.role = :role")
        void deleteAllByRole(Role role);


}
