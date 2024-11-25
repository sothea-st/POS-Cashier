package com.example.pos.connection1.feature.settings.range.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RangeRequest(
        @NotNull(message = "The field warehouseId is require!")
        Integer warehouseId,
        @NotBlank(message = "The field rangeNameEn is required!")
        String rangeNameEn,
        String rangeNameKh,
        @NotNull(message = "The field createBy is required!")
        Integer createBy
) {
}
