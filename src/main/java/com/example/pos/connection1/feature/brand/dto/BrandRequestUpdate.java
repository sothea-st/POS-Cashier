package com.example.pos.connection1.feature.brand.dto;

import com.example.pos.connection1.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;

public record BrandRequestUpdate(
    @NotBlank(message = JavaMessage.required)
    String brandNameEn,
    String brandNameKh
) {
    
}
