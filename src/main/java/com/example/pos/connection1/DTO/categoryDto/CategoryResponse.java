package com.example.pos.connection1.DTO.categoryDto;

import lombok.Builder;

@Builder
public record CategoryResponse(
     String catNameEn,
     int id,
     String catNameKh,
     int movePosition,
     int parentId
) {
     
}
