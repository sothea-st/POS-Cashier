package com.example.pos.connection1.service.addImageService;

import lombok.Builder;

@Builder
public record ImageResponse(
    String fileName,
    String uuid
) {
    
}
