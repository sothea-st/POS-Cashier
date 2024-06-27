package com.example.pos.connection1.entity;

import java.util.Date;

import com.example.pos.connection1.constant.JavaMessage;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 

    @Column(name = "cat_name_kh")
    private String catNameKh; 

    @Column(nullable = false,name = "cat_name_en")
    @NotBlank(message = JavaMessage.required)
    private String catNameEn;

    @Column(name = "parent_id")
    private int parentId=0;

    @Column(name = "move_position")
    private int movePosition;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @CreationTimestamp
    @Column(updatable = false,name = "create_date")
    private Date createDate;

    @Column(name = "create_by")
    private int createBy;

}
