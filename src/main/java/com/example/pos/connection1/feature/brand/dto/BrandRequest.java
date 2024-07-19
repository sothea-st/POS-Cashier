package com.example.pos.connection1.feature.brand.dto;

import com.example.pos.connection1.constant.JavaMessage;

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
