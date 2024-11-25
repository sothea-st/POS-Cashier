package com.example.pos.connection1.feature.settings.uom.dto;

import lombok.Builder;

@Builder
public record UomResponse(
        Integer id,
        String uomNameEn,
        String uomNameKh,
        Integer numberOfUnit
) {

}
