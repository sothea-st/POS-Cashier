package com.example.pos.system.feature.adjustment.dto.response;

import lombok.Builder;

@Builder
public record ReasonData(
        Integer id,
        String name
) {
}
