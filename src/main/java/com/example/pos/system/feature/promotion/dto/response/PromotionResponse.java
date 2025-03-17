package com.example.pos.system.feature.promotion.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PromotionResponse(
        Long id,
        String createdDate,
        String createdBy,
        String promotionType,
        String startDate,
        String endDate,
        String percentage,
        BigDecimal salePrice,
        BigDecimal afterDiscount,
        Boolean isStatus
) {
}
