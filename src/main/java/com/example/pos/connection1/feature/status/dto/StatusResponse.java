package com.example.pos.connection1.feature.status.dto;

import lombok.Builder;

@Builder
public record StatusResponse(
    Integer id,
    String statusName,
    Boolean status,
    Boolean isDeleted
) {

}
