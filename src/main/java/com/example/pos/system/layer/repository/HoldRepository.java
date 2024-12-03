package com.example.pos.system.layer.repository;

import com.example.pos.system.layer.repository.productProjection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.system.domain.general.Hold;
import com.example.pos.system.layer.projections.holdProjection.HoldDataProjection;
import com.example.pos.system.layer.projections.holdProjection.HoldProjection;

import java.util.*;

@Repository
public interface HoldRepository extends JpaRepository<Hold, Integer> {

        @Query(nativeQuery = true , value = "select\r\n" + //
                                "\t*\r\n" + //
                                "from\r\n" + //
                                "\tpos_hold\r\n" + //
                                "where\r\n" + //
                                "\tstatus = true\r\n" + //
                                "\tand is_deleted = false\r\n" + //
                                "\tand id = ?")
        Optional<Hold> getDataById(int id);

     @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name , \r\n" + //
                    "\t                        pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price ,ph.discount_type,pc.choices, \r\n" + //
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

     @Query(nativeQuery = true, value = "select ph.id,ph.note,ph.qty_hold  from pos_hold as ph\r\n" + //
               "where ph.status = true and ph.is_deleted = false and create_by=? and id = ?")
     List<HoldDataProjection> getHoldDataAll(int userId,int id);

     @Query(nativeQuery = true, value = "select\r\n" + //
                          "\tcount(*)\r\n" + //
                          "from\r\n" + //
                          "\tpos_hold ph\r\n" + //
                          "where\r\n" + //
                          "\tph.status = true\r\n" + //
                          "\tand ph.is_deleted = false\r\n" + //
                          "\tand create_by =?")
     long countResult(int userId);

     @Query(nativeQuery = true, value = "select\r\n" + //
                          "\t*\r\n" + //
                          "from\r\n" + //
                          "\tpos_hold\r\n" + //
                          "where\r\n" + //
                          "\tstatus = true\r\n" + //
                          "\tand is_deleted = false\r\n" + //
                          "\tand id = ?")
     Hold getById(int id);


      @Query(nativeQuery = true, value = "select pc.id,pc.barcode,pc.cat_id ,pc.brand_id ,pc.flag ,pc.weight ,pc.pro_image_name , \r\n" + //
                        "pc.brand_id ,pc.pro_name_en ,pc.pro_name_kh ,pc.cost,pc.price , \r\n" + //
                        "pc.product_status ,pc.discount ,pc.code_out_stock ,pc.code_expired  \r\n" + //
                        "from pos_product pc where pc.status=true and pc.is_deleted=false\r\n" + //
                        "and pc.cat_id = ?")
        List<ProductProjection> getDataByHoldId(int holdId);

}
