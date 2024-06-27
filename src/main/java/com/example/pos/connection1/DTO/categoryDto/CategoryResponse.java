package com.example.pos.connection1.DTO.categoryDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
public record CategoryResponse(
     String catNameEn,
     int id,
     String catNameKh,
     int movePosition,
     int parentId
) {
     
}
