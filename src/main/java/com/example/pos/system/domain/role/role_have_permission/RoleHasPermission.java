package com.example.pos.system.domain.role.role_have_permission;

import java.util.Date;

import com.example.pos.system.domain.role.Role;
import com.example.pos.system.domain.role.permission.Permission;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "pos_role_has_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleHasPermission {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     @ManyToOne
     @JoinColumn(name = "role_id")
     private Role role;

     @ManyToOne
     @JoinColumn(name = "permission_id")
     private Permission permission;

     @Column(name = "parent_id")
     private Integer parentId;

     @Column(name = "is_visible")
     private Boolean isVisible;

     @Column(name = "is_create")
     private Boolean isCreate;

     @Column(name = "is_view")
     private Boolean isView;

     @Column(name = "is_update")
     private Boolean isUpdate;

     @Column(name = "is_delete")
     private Boolean isDelete;

}
