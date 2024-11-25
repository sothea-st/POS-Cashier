package com.example.pos.system.feature.status.dto;

import com.example.pos.system.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;

public record StatusRequest(
    @NotBlank(message = JavaMessage.required)
    String statusName
) {

}
