package com.example.pos.system.feature.brand.dto;

import com.example.pos.system.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;

public record BrandRequestUpdate(
    @NotBlank(message = JavaMessage.required)
    String brandNameEn,
    String brandNameKh
) {
    
}
