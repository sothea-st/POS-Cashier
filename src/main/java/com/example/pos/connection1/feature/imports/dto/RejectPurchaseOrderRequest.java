package com.example.pos.connection1.feature.imports.dto;

import com.example.pos.connection1.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RejectPurchaseOrderRequest(
     @NotBlank(message = JavaMessage.required)
     String msg,

     @NotNull(message = JavaMessage.required)
     Integer rejectBy
) {
     
}
