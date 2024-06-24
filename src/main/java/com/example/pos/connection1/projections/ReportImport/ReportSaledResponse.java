package com.example.pos.connection1.projections.ReportImport;

import java.math.BigDecimal;

public record ReportSaledResponse(
     String saleDate,
     String proNameEn,
     String proImageName,
     int qty,
     BigDecimal price,
     BigDecimal amountWithTax,
     String taxType,
     BigDecimal totalSaledExcludeVAT,
     BigDecimal vatAmt,
     BigDecimal plt,
     BigDecimal netSale,
     BigDecimal cost,
     BigDecimal margin,
     
 
   
    
   
     String discountCase
) {
     
}
