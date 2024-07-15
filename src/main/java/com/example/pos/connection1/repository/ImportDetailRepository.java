package com.example.pos.connection1.repository;

import com.example.pos.connection1.entity.ImportDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


@Repository
public interface ImportDetailRepository extends JpaRepository<ImportDetail,Integer> {

    // Optional<ImportDetail> findByImpId(int impId);

    @Query(nativeQuery = true,value = "select * from pos_import_detail pid where status =true and is_deleted =false and pid.qty_old >= 0 and pid.pro_id = ? order by id desc limit 1")
    ImportDetail getDataImportDetail(int productId);

    @Query(nativeQuery = true,value = "select * from pos_import_detail pid where status =true and is_deleted =false and  pro_id =? order by id desc limit 1")
    Optional<ImportDetail> findByImpId(int proId);

    @Query(nativeQuery = true,value = "select * from pos_import_detail pid where status =true and is_deleted =false and  imp_id = ?")
    List<ImportDetail> getResultByImpId(int impId);

 

    @Query(nativeQuery = true , value = "select qty_old  from pos_import_detail pid where pro_id = ? order by id desc limit 1")
    Integer getQty(int proId);

    @Query(nativeQuery = true , value = "select\r\n" + //
                "\tpid.qty_old \r\n" + //
                "from\r\n" + //
                "\tpos_product pc\r\n" + //
                "inner join pos_import_detail pid on\r\n" + //
                "\tpid.pro_id = pc.id\r\n" + //
                "where\r\n" + //
                "\tpc.status = true\r\n" + //
                "\tand pc.is_deleted = false \r\n" + //
                "\tand pc.id = ? order by pid.id desc limit 1")
    int getOldQty(int proId);

}
