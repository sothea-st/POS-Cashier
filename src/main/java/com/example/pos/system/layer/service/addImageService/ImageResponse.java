package com.example.pos.system.layer.service.addImageService;

import lombok.Builder;

@Builder
public record ImageResponse(
    String fileName,
    String uuid
) {
    
}
