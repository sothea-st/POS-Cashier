package com.example.pos.connection1.feature.imports;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;
import java.time.*;
import com.example.pos.connection1.entity.Import;
import com.example.pos.connection1.projections.ReportImport.ReportImportProjection;
public interface ImportRepository extends JpaRepository<Import, Integer> {
     @Query(nativeQuery = true, value = "select count(*) from pos_import")
     int countRecord();

     Optional<Import> findByImpNo(String impNo);

     @Query(nativeQuery = true, value = "select * from get_import_details(?,?)")
     List<ReportImportProjection> getReport(LocalDate dateFrom, LocalDate dateTo);
     Page<Import> findByStatusTrueAndIsDeletedFalse(PageRequest pageRequest);

     Optional<Import> findByIdAndStatusTrueAndIsDeletedFalse(int id);
     Page<Import> findByDateLocalBetween(LocalDate dateFrom, LocalDate dateTo , PageRequest pageRequest);
     List<Import> findByDateLocalBetween(LocalDate dateFrom, LocalDate dateTo);
  
}
