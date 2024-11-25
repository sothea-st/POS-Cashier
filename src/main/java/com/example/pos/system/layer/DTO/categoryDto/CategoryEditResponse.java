package com.example.pos.system.layer.DTO.categoryDto;

import lombok.Builder;
@Builder
public record CategoryEditResponse<T>(
     String catNameEn,
     int id,
     String catNameKh,
     int movePosition,
     int parentId,
     String code,
     T department,
     T category,
     T subCategory
) {
     
}
