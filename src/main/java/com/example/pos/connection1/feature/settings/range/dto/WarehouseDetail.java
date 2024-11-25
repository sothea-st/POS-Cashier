package com.example.pos.connection1.feature.settings.range.dto;

import lombok.Builder;

@Builder
public record WarehouseDetail(
        Integer id,
        String warehouseNameEn,
        String warehouseNameKh
) {
}
