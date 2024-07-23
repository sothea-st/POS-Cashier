package com.example.pos.connection1.feature.imports.dto;

import java.math.BigDecimal;
import java.util.*;

import lombok.Builder;

@Builder
public record ImportResponseById(
     Integer transactionNo,
     String purchaseOrderNo,
     String transactionDate,
     Integer vendorId,
     String vendorName,
     Integer totalQty,
     BigDecimal totalCost,
     String referenceNo,
     String orderDate,
     String createBy,
     List<ImportDetailResponse> details
) {
     
}
