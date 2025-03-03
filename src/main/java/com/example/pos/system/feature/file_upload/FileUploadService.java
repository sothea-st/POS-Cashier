package com.example.pos.system.feature.file_upload;

import com.example.pos.system.feature.file_upload.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    FileUploadResponse fileUpload(MultipartFile file);
}
