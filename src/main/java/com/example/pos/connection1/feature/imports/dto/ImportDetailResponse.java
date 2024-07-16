package com.example.pos.connection1.feature.imports.dto;

import java.math.BigDecimal;

import lombok.Builder;
@Builder
public record ImportDetailResponse(
     Integer id,
     String barcode,
     String proNameEn,
     String proNameKh,
     String division,
     String department,
     String category,
     String subCategory,
     Integer subCategoryId,
     Integer availableQty,
     Integer orderQty,
     BigDecimal cost,
     BigDecimal totalCost
) {
     
}
