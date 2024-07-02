package com.example.pos.connection1.feature.uom.dto;

import com.example.pos.connection1.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;

public record UomRequest(
    @NotBlank(message = JavaMessage.required)
    String nameEn,
    String nameKh
) {
    
}
