package com.example.pos.system.feature.settings.range.dto;

import lombok.Builder;

@Builder
public record RangeResponse(
        Integer id,
        String rangeNameEn,
        String rangeNameKh,
        WarehouseDetail warehouse

) {
}
