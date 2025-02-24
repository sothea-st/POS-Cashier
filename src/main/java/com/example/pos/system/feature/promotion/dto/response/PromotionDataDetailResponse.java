package com.example.pos.system.feature.promotion.dto.response;

import lombok.*;

import java.math.BigDecimal;
@Builder
public record PromotionDataDetailResponse(
        Integer productId,
        String barcode,
        String category,
        String descEng,
        String descKhr,
        String division,
        String department,
        String percentage,
        BigDecimal salePrice,
        BigDecimal afterDiscount
) {
}
