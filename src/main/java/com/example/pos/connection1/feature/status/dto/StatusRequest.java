package com.example.pos.connection1.feature.status.dto;

import com.example.pos.connection1.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;

public record StatusRequest(
    @NotBlank(message = JavaMessage.required)
    String statusName
) {

}
