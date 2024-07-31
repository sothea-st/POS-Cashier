package com.example.pos.connection1.feature.reports.report_receive.dto;

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
