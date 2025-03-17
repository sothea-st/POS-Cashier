package com.example.pos.system.feature.order_online.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderProductDetailResponse(
        String barcode,
        String englishName,
        String khmerName,
        Integer qty,
        BigDecimal salePrice,
        String discountType,
        String discountPrice,
        BigDecimal total
) {
}
