package com.example.pos.system.domain.role.role_have_permission;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

     @Column(name = "role_id")
     private Integer roleId;
     
     @Column(name = "permission_id")
     private Integer permissionId;

     @Column(name = "is_visible")
     private Boolean isVisible;

     @Column(name = "is_create")
     private Boolean isCreate;

     @Column(name = "is_read")
     private Boolean isRead;

     @Column(name = "is_update")
     private Boolean isUpdate;

     @Column(name = "is_delete")
     private Boolean isDelete;

}
