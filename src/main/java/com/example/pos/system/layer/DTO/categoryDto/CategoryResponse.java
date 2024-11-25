package com.example.pos.system.layer.DTO.categoryDto;

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
