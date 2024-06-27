package com.example.pos.connection1.projections.ReportImport;

import java.math.BigDecimal;

import lombok.Builder;
@Builder
public record ReportSaledResponse(
     String saleDate,
     String proNameEn,
     String proImageName,
     int qty,
     String discountCase,
     double discountPercentage,
     double discount,
     BigDecimal price,
     BigDecimal amountWithTax,
     String taxType,
     BigDecimal totalSaledExcludeVAT,
     BigDecimal vatAmt,
     BigDecimal plt,
     BigDecimal netSale,
     BigDecimal cost,
     BigDecimal margin,
     String userName,
     String barcode
) {
     
}
