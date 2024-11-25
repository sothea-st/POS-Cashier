package com.example.pos.system.feature.attribute.dto;

import lombok.Builder;

@Builder
public record AttributeResponse(
    Integer id,
    String attrNameEn,
    String attrNameKh,
    Boolean status,
    Boolean isDeleted
) {

}
