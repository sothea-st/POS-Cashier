package com.example.pos.system.feature.adjustment.dto;

import jakarta.validation.constraints.NotNull;

public record AdjustmentDetailRequest(
//        @NotNull(message = "The field productId is required!")
        Integer productId,

//        @NotNull(message = "The field qty is required!")
        Long qty
) {
}
