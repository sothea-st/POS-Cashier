package com.example.pos.connection1.entity.sourceData;

import java.util.Date;

import com.example.pos.connection1.constant.JavaMessage;
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
@Table(name = "pos_brand")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand_name_en")
    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    private String brandNameEn;

    @Column(name = "brand_name_kh")
    private String brandNameKh;

    @Column(name = "create_by")
    @NotNull(message = JavaMessage.required)
    private Integer createBy;

    @CreationTimestamp
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "status")
    private boolean status=true;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

}
