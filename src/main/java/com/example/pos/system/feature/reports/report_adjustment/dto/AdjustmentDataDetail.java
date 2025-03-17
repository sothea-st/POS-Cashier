package com.example.pos.system.feature.reports.report_adjustment.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AdjustmentDataDetail(
        String transactionDate,
        String transactionNo,
        String reference,
        String reason,
        String description,
        Integer totalQty,
        BigDecimal totalCost
) {
}
