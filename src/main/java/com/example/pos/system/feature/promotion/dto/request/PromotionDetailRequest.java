package com.example.pos.system.feature.promotion.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

public record PromotionDetailRequest(

        Integer productId,

        Integer percentage,


        BigDecimal afterDiscount
) {
}
