package com.example.pos.connection1.feature.category;

import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface CategoryService {
    JavaCollectionResponse<?> read(Integer pageNumber , Integer pageSize , String code);

    JavaCollectionResponse<?> search(Integer pageNumber , Integer pageSize , String code , String value);
}
