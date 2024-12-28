package com.example.pos.system.feature.attribute.product.dto;

import com.example.pos.system.constant.JavaMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductDataRequest(
    @NotNull(message = JavaMessage.required)
    Integer catId ,
    @NotBlank(message = JavaMessage.required)
    String proNameEn,
    String proNameKh,
    @NotNull(message = JavaMessage.required)
    BigDecimal cost,
    @NotNull(message = JavaMessage.required)
    BigDecimal price,
    @NotBlank(message = JavaMessage.required)
    @Size(max = 13,min = 13,message = "barcode must be 13 digits.")
    @Pattern(regexp = "\\d{13}", message = "Barcode must contain only digits")
    String barcode,
    @NotNull(message = JavaMessage.required)
    Integer brandId,
    @NotNull(message = JavaMessage.required)
    Integer createBy,
    @NotNull(message = JavaMessage.required)
    Integer taxId,
    @NotNull(message = JavaMessage.required)
    Integer vendorId,
    @NotNull(message = JavaMessage.required)
    Integer countryId,
    @NotNull(message = JavaMessage.required)
    Integer productActiveId,
    @NotNull(message = JavaMessage.required)
    Integer uomId,
    @NotNull(message = JavaMessage.required)
    Integer attributeId,
    String choice,
    String margin
) {
}
