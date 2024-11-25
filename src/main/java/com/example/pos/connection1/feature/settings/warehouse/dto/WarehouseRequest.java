package com.example.pos.connection1.feature.settings.warehouse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WarehouseRequest(
        @NotBlank(message = "The field warehouseNameEn is required!")
        String warehouseNameEn,
        String warehouseNameKh,
        @NotNull(message = "The field createBy is required!")
        Integer createBy
) {
}
