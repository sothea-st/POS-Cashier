package com.example.pos.system.feature.promotion.dto.request;

import jakarta.validation.constraints.NotNull;

public record PromotionStatusRequest(
        @NotNull(message = "The field isStatus is required !")
        Boolean isStatus
) {
}
