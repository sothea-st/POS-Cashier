package com.example.pos.system.feature.imports.dto;

import com.example.pos.system.constant.JavaMessage;
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
