package com.example.pos.connection1.repository;

import com.example.pos.connection1.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportRepository extends JpaRepository<Import,Integer> {

    @Query(nativeQuery = true , value = "select count(*) from pos_import")
    int countRecord();
}
