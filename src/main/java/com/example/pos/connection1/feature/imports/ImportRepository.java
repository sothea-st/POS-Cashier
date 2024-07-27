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
     List<Import> findByStatusTrueAndIsDeletedFalseAndRemark(String remark);
     Page<Import> findByDateLocalBetweenAndCheckByAndRemark(LocalDate dateFrom, LocalDate dateTo , PageRequest pageRequest,int checkBy,String remark);

     List<Import> findByDateLocalBetweenAndCheckByAndRemark(LocalDate dateFrom, LocalDate dateTo ,int checkBy,String remark);

     Page<Import> findByDateLocalBetweenAndCreateByAndRemark(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int creataBy , String remark);

     List<Import> findByDateLocalBetweenAndCreateByAndRemark(LocalDate dateFrom , LocalDate dateTo  , int creataBy , String remark);
     List<Import> findByDateLocalBetweenAndCreateBy(LocalDate dateFrom , LocalDate dateTo  , int creataBy);

     List<Import> findByDateLocalBetweenAndApproveByAndRemark(LocalDate dateFrom , LocalDate dateTo , int approvedBy , String remark);

     Page<Import> findByDateLocalBetweenAndApproveByAndRemark(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int approvedBy , String remark);
     Page<Import> findByDateLocalBetweenAndRejectByAndRemark(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int rejectBy , String remark);
     List<Import> findByDateLocalBetweenAndRejectByAndRemark(LocalDate dateFrom , LocalDate dateTo , int rejectBy , String remark);

     Page<Import> findByDateLocalBetweenAndCreateBy(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int creataBy);
     Page<Import> findByDateLocalBetweenAndCheckBy(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int checkBy);
     List<Import> findByDateLocalBetweenAndCheckBy(LocalDate dateFrom , LocalDate dateTo  , int checkBy);


     Page<Import> findByDateLocalBetweenAndApproveBy(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int approvedBy);
     List<Import> findByDateLocalBetweenAndApproveBy(LocalDate dateFrom , LocalDate dateTo ,  int approvedBy);

     Page<Import> findByDateLocalBetweenAndRejectBy(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int rejectId);
     List<Import> findByDateLocalBetweenAndRejectBy(LocalDate dateFrom , LocalDate dateTo , int rejectId);

}
