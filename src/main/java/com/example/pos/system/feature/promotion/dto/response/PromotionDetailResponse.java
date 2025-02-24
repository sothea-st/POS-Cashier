package com.example.pos.system.feature.promotion.dto.response;

import lombok.Builder;


import java.util.List;

@Builder

public record PromotionDetailResponse(
        Long promotionId,
        String promotionType,
        String startDate,
        String endDate,
        Integer percentage,
        List<PromotionDataDetailResponse> details
) {
}
