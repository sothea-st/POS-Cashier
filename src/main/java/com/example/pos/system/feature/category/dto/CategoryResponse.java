package com.example.pos.system.feature.category.dto;

public record CategoryResponse(
    Integer id ,
    String catNameEn,
    String catNameKh,
    Integer movePosition,
    Integer parentId
) {
    
}
