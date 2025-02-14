package com.example.pos.system.domain.sourceData;

import java.sql.Date;

import com.example.pos.system.constant.JavaMessage;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_reason")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reason {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @NotBlank(message = JavaMessage.required)
     @NotNull(message = JavaMessage.required)
     @Column(name = "reason")
     private String reason;

     @Column(name = "code")
     private String code;

     @Column(name = "return_type")
     private String returnType;

     @Column(name = "create_by")
     private int createBy;

     @CreationTimestamp
     @Column(name = "create_date")
     private Date createDate;

     @Column(name = "status")
     private boolean status=true;

     @Column(name = "is_deleted")
     private boolean isDeleted=false;
     
}
