package com.example.pos.connection1.entity;

import java.util.Date;

import com.example.pos.connection1.constant.JavaMessage;
import com.example.pos.connection1.entity.sourceData.Brand;
import com.example.pos.connection1.entity.sourceData.TaxProduct;
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
    private Integer catId;

    @Column(name = "flag")
    private String flag;

    @Column(name = "weight")
    private String weight;

    @Column(name = "pro_image_name", length = 1000)
    private String proImageName;

    @Column(name = "note")
    private String note;

    @Column(name = "code_expired")
    private String codeExpired;

    @Column(name = "code_out_stock")
    private String codeOutStock;

    @Column(name = "barcode", unique = true)
    private String barcode;

    @Column(name = "pro_qty")
    private Integer proQty;

    //    ******************  add new ********************
    @ManyToOne
    @JoinColumn(name = "import_id")
    private ImportDetail importDetail;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "tax_id")
    private TaxProduct taxProduct;

    @ManyToOne
    @JoinColumn(name = "product_active_id")
    private Status productActive;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "uom_id")
    private Uom uom;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private Category subCategory;


//    ******************  add new ********************


    @Column(name = "choices")
    private String choices;

    @Column(name = "margin")
    private String margin;

    @Column(name = "pro_name_kh")
    private String proNameKh;

    @Column(name = "pro_name_en")
    private String proNameEn;

    @Column(precision = 10, scale = 2, name = "cost")
    private BigDecimal cost = new BigDecimal(0);

    @Column(precision = 10, scale = 2, name = "price")
    private BigDecimal price = new BigDecimal(0);

    @Column(name = "product_status")
    private String productStatus;

    @Column(name = "discount", precision = 10, scale = 2)
    private BigDecimal discount;

    @Column(name = "status")
    private Boolean status = true;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @CreationTimestamp
    @Column(updatable = false, name = "create_date")
    private Date createDate;

    @Column(name = "create_by")
    private Integer createBy;

}
