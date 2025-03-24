package com.example.pos.system.layer.repository;

import com.example.pos.system.domain.stock.Import;
import com.example.pos.system.layer.projections.ReportImport.ReportImportProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
@Repository
public interface ImportRepositoryOld extends JpaRepository<Import,Integer> {

    @Query(nativeQuery = true , value = "select count(*) from pos_import")
    int countRecord();


    @Query(nativeQuery = true , value = "select * from get_import_details(?,?)")
    List<ReportImportProjection> getReport(LocalDate dateFrom , LocalDate dateTo);


}
