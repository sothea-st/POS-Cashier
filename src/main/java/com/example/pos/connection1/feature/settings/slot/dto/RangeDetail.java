package com.example.pos.connection1.feature.settings.slot.dto;

import lombok.Builder;

@Builder
public record RangeDetail(
        Integer id,
        String rangeNameEn,
        String rangeNameKh
) {
}
