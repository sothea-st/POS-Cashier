package com.example.pos.system.feature.file_upload;

import com.example.pos.system.feature.file_upload.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service

public class FileUploadServiceImp implements FileUploadService{

    @Value("${server.path.image}")
    private String pathImage;

    @Override
    public FileUploadResponse fileUpload(MultipartFile file) {

        String fileName = UUID.randomUUID().toString();
        String extension = file.getOriginalFilename().split("\\.")[1];
        fileName = fileName + "." + extension;
        System.out.println("33333333333333333333333333 = " + fileName);
        Path path = Paths.get(pathImage + "/" + fileName);
        try {
            Files.copy(file.getInputStream(), path);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INSUFFICIENT_STORAGE, "Upload file failed : " +file.getSize());
        }


        return FileUploadResponse.builder()
                .fileName(fileName)
                .originalName(file.getOriginalFilename())
                .build();
    }
}
