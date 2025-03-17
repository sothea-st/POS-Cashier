package com.example.pos.system.feature.file_upload.dto;

import lombok.Builder;

@Builder
public record FileUploadResponse(
    String fileName,
    String originalName
) {
}
