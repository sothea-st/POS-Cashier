package com.example.pos.system.feature.imports.dto;

import com.example.pos.system.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RejectPurchaseOrderRequest(
     @NotBlank(message = JavaMessage.required)
     String msg,

     @NotNull(message = JavaMessage.required)
     Integer rejectBy
) {
     
}
