package com.example.pos.connection1.service.addImageService;

import java.io.IOException;

import com.example.pos.connection1.entity.FileStore;
import com.example.pos.connection1.repository.FileStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
@Service
public class AddImageService {
    @Autowired
    private FileStoreRepository repo;
    // this one is use
    public String addImage(MultipartFile file) throws IOException {
        String fName = UUID.randomUUID().toString();
        FileStore f = new FileStore(fName, fName, file.getContentType(), file.getBytes());
        repo.save(f);
        return fName;
    }

    // this one is use
    public String insertImage(MultipartFile file) throws IOException {
        String fName = file.getOriginalFilename();
        FileStore f = new FileStore(fName, fName, file.getContentType(), file.getBytes());
        repo.save(f);
        return fName;
    }

    public byte[] getFile(String id) throws IOException {
        Optional<FileStore> fileDB = repo.findById(id);
        return fileDB.get().getData();
    }
}
