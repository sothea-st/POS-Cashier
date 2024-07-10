package com.example.pos.connection1.feature.product.productV1.dto;

import com.example.pos.connection1.constant.JavaMessage;

import jakarta.validation.constraints.NotNull;

public record ProductRequestVendorOrSubCateId(
     @NotNull(message = JavaMessage.required)
     Integer  vendorId ,

     Integer subCatId
) {
     
}
