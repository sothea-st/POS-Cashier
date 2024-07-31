package com.example.pos.connection1.feature.category.dto;

public record CategoryResponse(
    Integer id ,
    String catNameEn,
    String catNameKh,
    Integer movePosition,
    Integer parentId
) {
    
}
