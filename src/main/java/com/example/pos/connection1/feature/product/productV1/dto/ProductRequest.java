package com.example.pos.connection1.feature.product.productV1.dto;

import com.example.pos.connection1.constant.JavaMessage;
import com.example.pos.connection1.entity.Attribute;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record ProductRequest(
        @NotNull(message = JavaMessage.required)
        Integer subCatId,
        
        String proNameKh,

        @NotBlank(message = JavaMessage.required)
        String proNameEn,

        @NotNull(message = JavaMessage.required)
        BigDecimal cost,

        @NotNull(message = JavaMessage.required)
        BigDecimal price,

        @NotBlank(message = JavaMessage.required)
        String margin,

        @NotNull(message = JavaMessage.required)
        Integer brandId,

        @NotBlank(message = JavaMessage.required)
        @Size(min = 13, max = 13, message = "Barcode must be exactly 13 digits.")
        @Pattern(regexp = "^[0-9]*$", message = "Barcode must contain only digits")
        String barcode,

        @NotNull(message = JavaMessage.required)
        Integer createBy,

        @NotNull(message = JavaMessage.required)
        Integer taxId,

        @NotNull(message = JavaMessage.required)
        Integer vendorId,

        @NotNull(message = JavaMessage.required)
        Integer uomId,

        @NotNull(message = JavaMessage.required)
        Integer attributeId,

        @NotNull(message = JavaMessage.required)
        Integer productActiveId,

        @NotNull(message = JavaMessage.required)
        Integer countryId,

        @NotBlank(message = JavaMessage.required)
        String choices,

        String proImageName

) {
}
