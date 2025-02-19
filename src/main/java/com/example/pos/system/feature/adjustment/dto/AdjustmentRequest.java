package com.example.pos.system.feature.adjustment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record AdjustmentRequest(
        @NotNull(message = "The field reasonId is required!")
        Integer reasonId,

        @NotBlank(message = "The field reference is required!")
        String reference,


        @NotBlank(message = "The field transactionDate is required!")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "The field transactionDate format is yyyy-MM-dd.")
        String transactionDate,

        String comment,


        @NotNull(message = "The field totalQty is required!")
        Long totalQty,

        @NotNull(message = "The field totalCost is required!")
        BigDecimal totalCost,

        @NotNull(message = "The field details is required!")
        @Size(min = 1, message = "The field details cannot be empty!")
        List<AdjustmentDetailRequest> details


) {
}
