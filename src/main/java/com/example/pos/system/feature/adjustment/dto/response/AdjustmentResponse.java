package com.example.pos.system.feature.adjustment.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record AdjustmentResponse(
    Long id,
    String transaction,
    String transactionDate,
    String postDate,
    String referenceName,
    String approvalUser,
    String reason,
    Long totalQty,
    BigDecimal totalCost,
    String status
) {
}
