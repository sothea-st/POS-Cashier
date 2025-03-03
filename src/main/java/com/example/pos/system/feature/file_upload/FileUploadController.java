package com.example.pos.system.feature.file_upload;

import com.example.pos.system.feature.file_upload.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/public/fileUploadPath")
@RequiredArgsConstructor
public class FileUploadController {
    // inject bean service
    private final FileUploadService fileUploadService;

    @PostMapping
    public FileUploadResponse create(@RequestPart MultipartFile file){
        return fileUploadService.fileUpload(file);
    }

}
