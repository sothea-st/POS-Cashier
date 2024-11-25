package com.example.pos.system.feature.settings.warehouse.dto;

import lombok.Builder;

@Builder
public record WarehouseResponse(
        Integer id,
        String warehouseNameEn,
        String warehouseNameKh
) {
}
