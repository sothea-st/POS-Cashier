package com.example.pos.connection1.feature.attribute.dto;

import com.example.pos.connection1.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;

public record AttributeUpdateRequest(
    @NotBlank(message = JavaMessage.required)
    String attrNameEn,
    String attrNameKh
) {

}
