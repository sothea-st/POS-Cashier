package com.example.pos.connection1.feature.uom.dto;

import lombok.Builder;

@Builder
public record UomResponse(
    String nameEn,
    String nameKh,
    Boolean status,
    Boolean isDeleted
) {

}
