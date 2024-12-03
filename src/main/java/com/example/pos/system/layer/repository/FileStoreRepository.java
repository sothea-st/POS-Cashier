package com.example.pos.system.layer.repository;

import com.example.pos.system.domain.general.FileStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStoreRepository extends JpaRepository<FileStore, String> {

}
