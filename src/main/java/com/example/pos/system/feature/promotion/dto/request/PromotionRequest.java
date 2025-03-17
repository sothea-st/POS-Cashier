package com.example.pos.system.feature.promotion.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record PromotionRequest(
        @NotBlank(message = "The field promotionType is required !")
        String promotionType,

        @NotBlank(message = "The field startDate is required!")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "The field startDate format is yyyy-MM-dd.")
        String startDate,

        @NotBlank(message = "The field endDate is required!")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "The field endDate format is yyyy-MM-dd.")
        String endDate,

        @NotNull(message = "The field percentage is required !")
        Integer percentage,

        @NotNull(message = "The field createdBy is required !")
        Integer createdBy,

        @NotNull(message = "The field afterDiscount is required !")
        BigDecimal afterDiscount,

        @NotNull(message = "The field totalPrice is required !")
        BigDecimal totalPrice,

        @NotNull(message = "The field details is required!")
        @Size(min = 1, message = "The field details cannot be empty!")
        List<PromotionDetailRequest> details

) {
}
