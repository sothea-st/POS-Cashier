package com.example.pos.connection1.feature.settings.uom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UomRequest(
        @NotBlank(message = "The field uomNameEn is required.")
        String uomNameEn,
        String uomNameKh,
        Integer numberOfUnit,
        @NotNull(message = "The field createdBy is required.")
        Integer createdBy
) {
    
}
