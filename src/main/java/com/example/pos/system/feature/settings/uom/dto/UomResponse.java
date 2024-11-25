package com.example.pos.system.feature.settings.uom.dto;

import lombok.Builder;

@Builder
public record UomResponse(
        Integer id,
        String uomNameEn,
        String uomNameKh,
        Integer numberOfUnit
) {

}
