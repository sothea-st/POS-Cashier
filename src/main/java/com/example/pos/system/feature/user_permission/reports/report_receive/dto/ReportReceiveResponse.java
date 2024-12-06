package com.example.pos.system.feature.user_permission.reports.report_receive.dto;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record ReportReceiveResponse(
        String vendorName ,
        String transactionNo,
        String referenceNo,
        String transactionDate,
        String receiveBy,
        Integer totalQty,
        BigDecimal totalCost,
        String remark
) {
}
