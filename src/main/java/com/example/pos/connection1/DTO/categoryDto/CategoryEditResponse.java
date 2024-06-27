package com.example.pos.connection1.DTO.categoryDto;

import java.util.Map;

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
