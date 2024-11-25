package com.example.pos.system.feature.brand.dto;

import com.example.pos.system.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BrandRequest(
    @NotBlank(message = JavaMessage.required)
    String brandNameEn,
    String brandNameKh,
    @NotNull(message = JavaMessage.required)
    Integer createBy


) {

}
