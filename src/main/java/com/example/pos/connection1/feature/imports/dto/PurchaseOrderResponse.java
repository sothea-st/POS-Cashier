package com.example.pos.connection1.feature.imports.dto;

import lombok.Builder;

@Builder
public record PurchaseOrderResponse(
     Integer id ,
     String poId
) {
     
}
