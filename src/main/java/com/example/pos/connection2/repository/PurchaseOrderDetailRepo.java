package com.example.pos.connection2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.connection2.entity.PurchaseOrderDetail;
@Repository
public interface PurchaseOrderDetailRepo extends JpaRepository<PurchaseOrderDetail,Integer> {
     @Query(nativeQuery = true , value = "SELECT p.order_qty FROM purchase_order_detail as p where p.product_id = ? order by id desc limit 1")
     int getOrderQty(int productId);


     @Query(nativeQuery = true , value = "SELECT p.id FROM purchase_order_detail as p where p.product_id = ? order by id desc limit 1")
     int getPurchaseId(int productId);
}
