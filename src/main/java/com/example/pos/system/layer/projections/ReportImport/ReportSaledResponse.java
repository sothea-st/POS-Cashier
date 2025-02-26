package com.example.pos.system.layer.projections.ReportImport;

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
     String barcode,
     String invoiceNumber,
     String transactionType,
     String posId,
     String paymentStatus,
     String paymentMethod,
     BigDecimal totalOrder,
     String orderSource,
     String customerType,
     BigDecimal khqrCode
) {
     
}
