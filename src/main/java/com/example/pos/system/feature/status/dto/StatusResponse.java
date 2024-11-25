package com.example.pos.system.feature.status.dto;

import lombok.Builder;

@Builder
public record StatusResponse(
    Integer id,
    String statusName,
    Boolean status,
    Boolean isDeleted
) {

}
