package com.example.pos.system.constant.util.collection_response;

import lombok.Builder;

@Builder
public record JavaCollectionResponse<T>(
    long count,
    T data
) {
    
}
