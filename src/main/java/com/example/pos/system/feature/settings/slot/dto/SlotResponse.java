package com.example.pos.system.feature.settings.slot.dto;

import lombok.Builder;

@Builder
public record SlotResponse(
        Integer id,
        String slotNameEn,
        String slotNameKh,
        RangeDetail range
) {
}
