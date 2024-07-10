package com.example.pos.connection1.feature.product.productV1.dto;
import java.math.*;

import lombok.Builder;
@Builder
public record ProductResponseByFilter(
     Integer id,
     String barcode,
     String proNameEn,
     String division,
     Integer availableQty,
     Integer qty,
     BigDecimal cost,
     BigDecimal amount
) {
     
}
