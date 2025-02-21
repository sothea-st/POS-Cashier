package com.example.pos.system.feature.promotion.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ListProductResponse(
        Integer productId,
        String barcode,
        String categoryName,
        String englishDescription,
        Integer onHandQty,
        BigDecimal salePrice

) {
}
