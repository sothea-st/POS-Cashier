package com.example.pos.connection1.feature.imports.dto;

import com.example.pos.connection1.constant.JavaMessage;
import java.math.*;
import jakarta.validation.constraints.NotNull;

public record ImportDetailsRequest(
     @NotNull(message = JavaMessage.required)
     Integer productId,
     @NotNull(message = JavaMessage.required)
     Integer qtyNew,
     Integer id,
     BigDecimal cost,
     BigDecimal amount,
     String expireDate,
     Integer receivedQty
) {
     
}
