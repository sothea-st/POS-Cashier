package com.example.pos.connection1.feature.tax.dto;

import com.example.pos.connection1.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record TaxRequestUpdate(
    @NotBlank(message = JavaMessage.required)
    String taxName,
    BigDecimal rateTax
) {

}
