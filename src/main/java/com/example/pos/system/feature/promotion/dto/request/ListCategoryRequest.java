package com.example.pos.system.feature.promotion.dto.request;

import java.util.List;

public record ListCategoryRequest(
        List<CategoryIdRequest> listCategoryId
) {
}
