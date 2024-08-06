package com.example.pos.connection1.feature.product.productV1.dto;

import lombok.Builder;

import java.math.*;
@Builder
public record ProductResponse(
          Integer id, // productId
          String subCatNameEn,
          String proNameKh,
          String proNameEn,
          BigDecimal cost,
          BigDecimal price,
          String margin,
          String brandNameEn,
          String barcode,
          Integer createBy,
          String taxName,
          String vendorName,
          String uomNameEn,
          String attrNameEn,
          String statusName,
          String countryImageName,
          String choices,
          String proImageName,
          Integer qty,
          String itemCode,
          String vendorCode
          ) {
}
