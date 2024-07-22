package com.example.pos.connection1.feature.imports.dto;

import com.example.pos.connection1.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CheckingRequest(
     @NotNull(message = JavaMessage.required)
     Integer createBy,

     @NotBlank(message = JavaMessage.required)
     String remark,

     @NotBlank(message = JavaMessage.required)
     String role
) {
     
}
