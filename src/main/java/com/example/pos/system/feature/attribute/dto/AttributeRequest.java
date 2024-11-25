package com.example.pos.system.feature.attribute.dto;

import com.example.pos.system.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;

public record AttributeRequest(
    @NotBlank(message = JavaMessage.required)
    String attrNameEn,
    String attrNameKh
) {
    
}
