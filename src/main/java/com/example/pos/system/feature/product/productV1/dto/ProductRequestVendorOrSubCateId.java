package com.example.pos.system.feature.product.productV1.dto;

import com.example.pos.system.constant.JavaMessage;

import jakarta.validation.constraints.NotNull;

public record ProductRequestVendorOrSubCateId(
     @NotNull(message = JavaMessage.required)
     Integer  vendorId ,

     Integer subCatId
) {
     
}
