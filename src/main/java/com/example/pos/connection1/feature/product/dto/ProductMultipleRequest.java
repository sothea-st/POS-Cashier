package com.example.pos.connection1.feature.product.dto;

import java.math.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductMultipleRequest {
    private String barcode;
    private Integer vendorId;
    private Integer brandId;
    private Integer catId;
    private String productName;
    private String productNameKh;
    private BigDecimal cost;
    private  BigDecimal price;
    private  String margin;
    private  Integer attributeId;
    private  String choiceValue;
    private  Integer uomId;
    private Integer status;
    private  Integer countryId;
    private  Integer taxId;
    private String link;
    private String photo;
    private String number;
    private Integer createBy;
}
