package com.example.pos.connection1.feature.settings.warehouse.dto;

import lombok.Builder;

@Builder
public record WarehouseResponse(
        Integer id,
        String warehouseNameEn,
        String warehouseNameKh
) {
}
