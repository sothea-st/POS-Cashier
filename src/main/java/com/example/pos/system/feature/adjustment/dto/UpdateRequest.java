package com.example.pos.system.feature.adjustment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateRequest(
        @NotNull(message = "The field approvalBy is required!")
        Integer approvalBy,

        @NotBlank(message = "The field status is required")
        String status
) {
}
