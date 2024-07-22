package com.example.pos.connection1.feature.imports;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.connection1.entity.ImportDetail;
import com.example.pos.connection1.entity.ImportDetailTemporary;

@Repository
public interface ImportDetailTemporaryRepository extends JpaRepository<ImportDetailTemporary, Integer> {
     @Query(nativeQuery = true, value = "select * from pos_import_detail pid where status =true and is_deleted =false and pid.qty_old >= 0 and pid.pro_id = ? order by id desc limit 1")
     ImportDetailTemporary getDataImportDetail(int productId);

     @Query(nativeQuery = true, value = "select * from pos_import_detail_temporary pid where status =true and is_deleted =false and  imp_id = ?")
     List<ImportDetailTemporary> getResultByImpId(int impId);

}
