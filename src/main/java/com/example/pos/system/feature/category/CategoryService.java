package com.example.pos.system.feature.category;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

public interface CategoryService {
    JavaCollectionResponse<?> read(Integer pageNumber , Integer pageSize , String code);

    JavaCollectionResponse<?> search(Integer pageNumber , Integer pageSize , String code , String value);
}
