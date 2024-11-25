package com.example.pos.system.feature.settings.slot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SlotRequest(
        @NotNull(message = "The field rangeId is required!")
        Integer rangeId,
        @NotBlank(message = "The field slotNameEn is required!")
        String slotNameEn,
        String slotNameKh,
        @NotNull(message = "The field createBy is required!")
        Integer createBy
) {
}
