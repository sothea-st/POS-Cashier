package com.example.pos.system.feature.gazetteer.dto;

import lombok.Builder;

@Builder
public record GazetteerResponse(
        Integer id,
        String nameKh,
        String nameLatin
) {
}
