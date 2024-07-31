package com.example.pos.connection1.feature.category;

import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface CategoryService {
    JavaCollectionResponse<?> read(int pageNumber , int pageSize , String code);
}
