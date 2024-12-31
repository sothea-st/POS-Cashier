package com.example.pos.system.feature.product.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductResponse {
     private String barcode;
     private Integer vendorId;
     private Integer brandId;
     private Integer catId;
     private String productName;
     private String productNameKh;
     private BigDecimal cost;
     private BigDecimal price;
     private String margin;
     private Integer attributeId;
     private String choiceValue;
     private Integer uomId;
     private Integer status;
     private Integer countryId;
     private Integer taxId;
     private String path;
}
