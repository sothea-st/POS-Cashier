package com.example.pos.connection1.entity;

import java.util.Date;

import com.example.pos.connection1.constant.JavaMessage;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.*;
@Entity
@Table(name = "pos_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cat_id")
    private int catId;
    
    @Column(name = "brand_id")
    private int brandId;

    @Column(name = "flag" )
    private String flag;

    @Column(name = "weight")
    private String weight;

    @Column(name = "pro_image_name",length = 1000)
    private String proImageName;

    @Column(name = "note")
    private String note;

    @Column(name = "code_expired")
    private String codeExpired;

    @Column(name = "code_out_stock" )
    private String codeOutStock;

    @Column(name = "barcode",unique = true)
    private String barcode;

    @Column(name = "tax_id")
    @Min(1)
    private int taxId;

    @Column(name = "pro_qty")
    private Integer proQty;

    @Column(name = "product_active")
    @NotBlank(message = JavaMessage.required)
    private String productActive;

    
    @Column(name = "vendor_id")
    @NotNull(message = "the field vendorId is required.")
    private Integer vendorId; // uuid is secondary primary key 

    @Column(name = "country_id")
    // @NotBlank(message = "the field countryUuid is required.")
    private Integer countryId;

    @Column(name = "uom_id")
    // @NotBlank(message = "the field uomUuid is required.")
    private Integer uomId;

    @Column(name = "attribute_id")
    // @NotBlank(message = "the field attributeUuid is required.")
    private Integer attributeId;

    @Column(name = "choices")
    // @NotBlank(message = "the field attributeUuid is required.")
    private String choices;

    @Column(name = "margin")
    private BigDecimal margin;
 

    @Column(name = "pro_name_kh")
    // @NotBlank(message = JavaMessage.required)
    private String proNameKh;

    @Column(name = "pro_name_en")
    // @NotBlank(message = JavaMessage.required)
    private String proNameEn;

    @Column(precision = 10, scale = 2,name = "cost")
    private BigDecimal cost = new BigDecimal(0);

    @Column(precision = 10, scale = 2,name = "price")
    private BigDecimal price = new BigDecimal(0);

    @Column(name = "product_status")
    private String productStatus;

    @Column(name = "discount",precision = 10, scale = 2)
    private BigDecimal discount;

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
