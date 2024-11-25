package com.example.pos.system.feature.settings.slot.dto;

import lombok.Builder;

@Builder
public record RangeDetail(
        Integer id,
        String rangeNameEn,
        String rangeNameKh
) {
}
