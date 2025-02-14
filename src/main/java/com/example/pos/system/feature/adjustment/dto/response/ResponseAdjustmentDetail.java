package com.example.pos.system.feature.adjustment.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ResponseAdjustmentDetail(
        String itemCode,
        String barcode,
        String productNameEn,
        String productNameKh,
        String oum,
        Integer onHandQty,
        Long adjustQty,
        BigDecimal cost
) {
}
