package com.example.pos.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.sourceData.ReturnDetails;

@Repository
public interface ReturnDetailsRepository extends JpaRepository<ReturnDetails ,Integer> {
     @Query(nativeQuery = true , value = "select pp.sale_id  from pos_payment pp inner join pos_sale ps on ps.id = pp.sale_id  where pp.payment_no = ? and ps.sale_date = ?")
     int getSaleId(String paymentNo,String date);
}
