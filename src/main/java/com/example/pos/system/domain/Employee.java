package com.example.pos.system.domain;

import com.example.pos.system.constant.JavaMessage;
import com.example.pos.system.domain.role.Role;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "pos_employee")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_kh")
    private String nameKh;

    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    @Column(name = "name_en")
    private String nameEn;

    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    @Column(name = "gender")
    private String gender;

    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    @Column(name = "dob")
    private String dob;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "image_name")
    private String imageName;

    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    @Column(length = 12)
    private String contact;

    @ManyToOne
    @JoinColumn(name = "role_id")
    // @Column(name = "role_id")
    private Role roleId;

    private String address;

    @Column(name = "create_by")
    private int createBy;

    @CreationTimestamp
    @Column(updatable = false,name = "create_date")
    private Date createDate;

    @Column(name = "status")
    private boolean status=true;

    @Column(name = "is_deleted")
    private boolean isDeleted=false;

}
