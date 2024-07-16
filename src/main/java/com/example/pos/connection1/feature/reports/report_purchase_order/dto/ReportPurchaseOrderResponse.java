package com.example.pos.connection1.feature.reports.report_purchase_order.dto;
import java.math.BigDecimal;

import lombok.Builder;
@Builder
public record ReportPurchaseOrderResponse(
     String purchaseOrderNo,
     Integer transactionNo,
     String transactionDate,
     String orderDate,
     String referenceNo,
     String vendorName,
     Integer totalQty,
     BigDecimal totalCost
) {
     
}
