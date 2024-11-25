package com.example.pos.system.feature.imports.dto;
import java.math.*;

import lombok.Builder; 
@Builder
public record ImportResponse(
     Integer id,
     String transactionNo,
     String vendorName,
     String referenceNo,
     String transactionDate,
     Integer totalQty,
     BigDecimal totalCost,
     String remark
) {
     
}
