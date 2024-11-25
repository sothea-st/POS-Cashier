package com.example.pos.system.feature.tax.dto;

import com.example.pos.system.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record TaxRequest(
    @NotBlank(message = JavaMessage.required)
    String taxName,
    BigDecimal rateTax,
    Integer createBy
) {

}
