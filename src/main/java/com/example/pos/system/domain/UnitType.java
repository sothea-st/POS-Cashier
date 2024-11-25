package com.example.pos.system.domain;

import java.util.Date;

import com.example.pos.system.constant.JavaMessage;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_unit_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = JavaMessage.required)
    private String unitTypeNameKh;

    @Column(nullable = false , unique = true)
    @NotBlank(message=JavaMessage.required)
    private String unitTypeNameEn;
    @Column(name = "status")
   private boolean status=true;

   @Column(name = "is_deleted")
   private boolean isDeleted=false;

   @CreationTimestamp
   @Column(updatable = false, name = "create_data")
   private Date createDate;

   @Column(name = "create_by")
   private int createBy;
    
}
