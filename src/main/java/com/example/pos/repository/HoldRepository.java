package com.example.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.Hold;
import com.example.pos.entity.HoldeDetails;
import com.example.pos.projections.holdProjection.HoldDataProjection;
import com.example.pos.projections.holdProjection.HoldDetailsProjection;
import com.example.pos.projections.holdProjection.HoldProjection;
import com.example.pos.repository.productProjection.ProductProjection;

import java.util.*;

@Repository
public interface HoldRepository extends JpaRepository<Hold, Integer> {

        @Query(nativeQuery = true , value = "select * from pos_hold where status = true and is_deleted = false and id = ?")
        Optional<Hold> getDataById(int id);

     @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name , \r\n" + //
                    "\t                        pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price ,ph.discount_type, \r\n" + //
                    "\t                        pc.product_status ,ph.discount ,pc.code_out_stock ,pc.code_expired,ph.qty_hold as qty  \r\n" + //
                    "\t                        from pos_hold_details  as ph inner join pos_product pc on pc.id = ph.pro_id  \r\n" + //
                    "\t                        where ph.hold_id = ? and ph.status = true and ph.is_deleted = false")
     List<HoldProjection> getHoldDataById(int id);

     @Query(nativeQuery = true, value = "\t   select ph.id,ph.note,ph.qty_hold  from pos_hold as ph\r\n" + //
     "\t   where ph.status = true and ph.is_deleted = false and ph.id = ?")
     HoldDataProjection getData(int id);

 
     @Query(nativeQuery = true, value = "select ph.id,ph.note,ph.qty_hold  from pos_hold as ph\r\n" + //
               "where ph.status = true and ph.is_deleted = false and create_by=?")
     List<HoldDataProjection> getHoldDataAll(int userId);

     @Query(nativeQuery = true, value = "select count(*) from pos_hold ph where ph.status = true and ph.is_deleted = false and create_by=?")
     long countResult(int userId);

     @Query(nativeQuery = true, value = "select * from pos_hold where status=true and is_deleted = false and id = ?")
     Hold getById(int id);


      @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name , \r\n" + //
                        "pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price , \r\n" + //
                        "pc.product_status ,pc.discount ,pc.code_out_stock ,pc.code_expired  \r\n" + //
                        "from pos_product pc where pc.status=true and pc.is_deleted=false\r\n" + //
                        "and pc.cat_id = ?")
        List<ProductProjection> getDataByHoldId(int holdId);

}
