package com.example.pos.connection1.repository.sourceDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.connection1.entity.sourceData.ReturnDetails;

@Repository
public interface ReturnDetailsRepository extends JpaRepository<ReturnDetails ,Integer> {
     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\tpp.sale_id\r\n" + //
                    "from\r\n" + //
                    "\tpos_payment pp\r\n" + //
                    "inner join pos_sale ps on\r\n" + //
                    "\tps.id = pp.sale_id\r\n" + //
                    "where\r\n" + //
                    "\tpp.payment_no = ?\r\n" + //
                    "\tand ps.sale_date = ?")
     int getSaleId(String paymentNo,String date);
}
