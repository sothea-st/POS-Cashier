package com.example.pos.system.feature.adjustment.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record AdjustmentResponseDetail(


        ReasonData reason,
        String reference,
        String transactionDate,
        String comment,
        Long totalQty,
        BigDecimal totalCost,
        String transaction,
        List<ResponseAdjustmentDetail> details

) {
}
