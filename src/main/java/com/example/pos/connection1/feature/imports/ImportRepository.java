package com.example.pos.connection1.feature.imports;
import com.example.pos.connection1.entity.Vendor;
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
     Page<Import> findByStatusTrueAndIsDeletedFalseAndRemark(PageRequest pageRequest,String remark);



     Page<Import> findByStatusTrueAndIsDeletedFalse(PageRequest pageRequest);
     List<Import> findByStatusTrueAndIsDeletedFalse();

     Optional<Import> findByIdAndStatusTrueAndIsDeletedFalse(int id);
     Page<Import> findByDateLocalBetween(LocalDate dateFrom, LocalDate dateTo , PageRequest pageRequest);


     Page<Import> findByDateLocalBetweenAndRemarkIn(LocalDate dateFrom, LocalDate dateTo , List<String> remark,  PageRequest pageRequest);


     Page<Import> findByDateLocalBetweenAndVendor_VendorNameContainingIgnoreCase(LocalDate dateFrom, LocalDate dateTo, String vendorName, PageRequest pageRequest);
     List<Import> findByDateLocalBetweenAndVendor_VendorNameContainingIgnoreCase(LocalDate dateFrom, LocalDate dateTo, String vendorName);

     Page<Import> findByDateLocalBetweenAndVendor_VendorNameContainingIgnoreCaseAndReceiveBy(LocalDate dateFrom, LocalDate dateTo, String vendorName, int receiveBy,PageRequest pageRequest);
     List<Import> findByDateLocalBetweenAndVendor_VendorNameContainingIgnoreCaseAndReceiveBy(LocalDate dateFrom, LocalDate dateTo, String vendorName, int receiveBy);



     Page<Import> findByDateLocalBetweenAndReceiveBy(LocalDate dateFrom, LocalDate dateTo , PageRequest pageRequest , int receiveBy);

     Page<Import> findByDateLocalBetweenAndReceiveByAndRemarkIn(LocalDate dateFrom, LocalDate dateTo , PageRequest pageRequest , int receiveBy,List<String> remark);


     List<Import> findByDateLocalBetweenOrderByCreateDateDesc(LocalDate dateFrom, LocalDate dateTo);

     List<Import> findByDateLocalBetweenAndRemarkInOrderByCreateDateDesc(LocalDate dateFrom, LocalDate dateTo,List<String> remarks);

     List<Import> findByDateLocalBetweenAndReceiveByOrderByCreateDateDesc(LocalDate dateFrom, LocalDate dateTo,int receiveId);

     List<Import> findByDateLocalBetweenAndReceiveByAndRemarkInOrderByCreateDateDesc(LocalDate dateFrom, LocalDate dateTo,int receiveId,List<String> remarks);



     List<Import> findByStatusTrueAndIsDeletedFalseAndRemark(String remark);
     Page<Import> findByDateLocalBetweenAndCheckByAndRemark(LocalDate dateFrom, LocalDate dateTo , PageRequest pageRequest,int checkBy,String remark);

     List<Import> findByDateLocalBetweenAndCheckByAndRemarkOrderByCreateDateDesc(LocalDate dateFrom, LocalDate dateTo ,int checkBy,String remark);

     Page<Import> findByDateLocalBetweenAndCreateByAndRemark(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int creataBy , String remark);
     Page<Import> findByDateLocalBetweenAndCreateByAndCheckBy(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int creataBy , int checkBy);
     List<Import> findByDateLocalBetweenAndCreateByAndCheckByOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo , int creataBy , int checkBy);

     Page<Import> findByDateLocalBetweenAndCreateByAndCheckByAndApproveByAndRemark(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int creataBy , int checkBy,int approvedBy , String remark);
     List<Import> findByDateLocalBetweenAndCreateByAndCheckByAndApproveByAndRemarkOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo , int creataBy , int checkBy,int approvedBy , String remark);
     Page<Import> findByDateLocalBetweenAndCreateByAndCheckByAndApproveBy(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int creataBy , int checkBy,int approvedBy);

     List<Import> findByDateLocalBetweenAndCreateByAndCheckByAndApproveByOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo , int creataBy , int checkBy,int approvedBy);


     Page<Import> findByDateLocalBetweenAndCreateByAndCheckByAndRemark(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int creataBy , int checkBy,String remark);

     List<Import> findByDateLocalBetweenAndCreateByAndCheckByAndRemarkOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo , int creataBy , int checkBy,String remark);

     List<Import> findByDateLocalBetweenAndCreateByAndRemarkOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo  , int creataBy , String remark);
     List<Import> findByDateLocalBetweenAndCreateByOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo  , int creataBy);

     List<Import> findByDateLocalBetweenAndApproveByAndRemarkOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo , int approvedBy , String remark);

     Page<Import> findByDateLocalBetweenAndApproveByAndRemark(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int approvedBy , String remark);
     Page<Import> findByDateLocalBetweenAndRejectByAndRemark(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int rejectBy , String remark);
     List<Import> findByDateLocalBetweenAndRejectByAndRemarkOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo , int rejectBy , String remark);

     Page<Import> findByDateLocalBetweenAndCreateBy(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int creataBy);
     Page<Import> findByDateLocalBetweenAndCheckBy(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int checkBy);
     List<Import> findByDateLocalBetweenAndCheckByOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo  , int checkBy);


     Page<Import> findByDateLocalBetweenAndApproveBy(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int approvedBy);
     List<Import> findByDateLocalBetweenAndApproveByOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo ,  int approvedBy);

     Page<Import> findByDateLocalBetweenAndRejectBy(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int rejectId);

     Page<Import> findByDateLocalBetweenAndRejectByAndCreateBy(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int rejectId,int creataBy);

     List<Import> findByDateLocalBetweenAndRejectByAndCreateByOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo , int rejectId,int creataBy);
     List<Import> findByDateLocalBetweenAndRejectByAndCreateByAndCheckByOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo ,  int rejectId,int creataBy,int checkBy);

     Page<Import> findByDateLocalBetweenAndRejectByAndCreateByAndCheckBy(LocalDate dateFrom , LocalDate dateTo , PageRequest pageRequest , int rejectId,int creataBy,int checkBy);


     List<Import> findByDateLocalBetweenAndRejectByOrderByCreateDateDesc(LocalDate dateFrom , LocalDate dateTo , int rejectId);

}
