package com.example.pos.connection1.util.collection_response;

import lombok.Builder;

@Builder
public record JavaCollectionResponse<T>(
    long count,
    T content
) {
    
}
