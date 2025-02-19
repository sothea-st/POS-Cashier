package com.example.pos.system.feature.promotion.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

public record PromotionDetailRequest(
        @NotNull(message = "The field productId is required !")
        Integer productId,

        @NotNull(message = "The field percentage is required !")
        Integer percentage,

        @NotNull(message = "The field afterDiscount is required!")
        BigDecimal afterDiscount
) {
}
