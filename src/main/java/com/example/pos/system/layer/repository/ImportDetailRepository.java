package com.example.pos.system.layer.repository;

import com.example.pos.system.domain.stock.ImportDetail;

import com.example.pos.system.domain.settings.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;


@Repository
public interface ImportDetailRepository extends JpaRepository<ImportDetail,Integer> {

    @Query(nativeQuery = true,value = "select sum(pid.qty_old)  from pos_import_detail pid where pro_id = ?")
    Integer sumQtyByProId(Integer productId);


    // Optional<ImportDetail> findByImpId(int impId);

    @Query(nativeQuery = true,value = "select * from pos_import_detail pid where status =true and is_deleted =false and pid.qty_old >= 0 and pid.pro_id = ? order by id desc limit 1")
    ImportDetail getDataImportDetail(int productId);


    List<ImportDetail> findByProductAndStatusTrueAndIsDeletedFalseAndQtyOldGreaterThanOrderByCreateDateAsc(Optional<Product> product, int qtyOld);

    List<ImportDetail> findByProductAndStatusTrueAndIsDeletedFalseAndQtyOldGreaterThanOrderByCreateDateAsc(Product product, int qtyOld);

    @Query(nativeQuery = true,value = "select * from pos_import_detail pid where imp_id = ? and pro_id = ? and status = true and is_deleted =false")
    ImportDetail getImpIdAndProduct(int impId,int productId);


    @Query(nativeQuery = true,value = "SELECT * \n" +
            "FROM pos_import_detail pid \n" +
            "WHERE imp_id = ? \n" +
            "  AND pro_id = ? \n" +
            "  AND status = true \n" +
            "  AND is_deleted = false \n" +
            "  AND local_date = ?")
    ImportDetail getImpIdAndProductAndLocalDate(int impId, int productId, LocalDate localDate);

    @Query(nativeQuery = true,value = "select * from pos_import_detail pid where status =true and is_deleted =false and  pro_id =? order by id desc limit 1")
    Optional<ImportDetail> findByImpId(int proId);

    @Query(nativeQuery = true,value = "select * from pos_import_detail pid where status =true and is_deleted =false and  imp_id = ?")
    List<ImportDetail> getResultByImpId(int impId);

 

    @Query(nativeQuery = true , value = "select sum(qty_old)  from pos_import_detail pid where pro_id = ?  and qty_old > 0 and status = true and is_deleted = false")
    Integer getQty(int proId);

    @Query(nativeQuery = true , value = "select sum(pid.qty_old) from pos_import_detail pid where pro_id = ? and qty_old > 0 and is_deleted = false and status = true;")
    Integer sumQtyByProId(int proId);

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
