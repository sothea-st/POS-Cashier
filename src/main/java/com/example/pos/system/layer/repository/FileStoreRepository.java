package com.example.pos.system.layer.repository;

import com.example.pos.system.domain.general.FileStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Optional;

@Repository
public interface FileStoreRepository extends JpaRepository<FileStore, String> {


    Optional<FileStore> findByName(String name);
}
