package com.example.pos.system.feature.reports.report_purchase_order.dto;

import java.math.BigDecimal;

import lombok.Builder;
@Builder
public record ReportPOResponse(
          String purchaseOrderNo,
          String transactionNo,
          String transactionDate,
          String orderDate,
          String referenceNo,
          String vendorName,
          Integer totalQty,
          BigDecimal totalCost,
          String requestBy,
          String requestDate,
          String checkBy,
          String checkDate,
          String approvedBy,
          String approvedDate,
          String rejectBy,
          String rejectDate,
          String remark
          ) {

}
