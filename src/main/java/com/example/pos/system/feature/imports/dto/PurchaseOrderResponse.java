package com.example.pos.system.feature.imports.dto;

import lombok.Builder;

@Builder
public record PurchaseOrderResponse(
     Integer id ,
     String poId
) {
     
}
