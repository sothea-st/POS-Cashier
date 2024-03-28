package com.example.pos.repository.paymentRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.example.pos.entity.Company;
import com.example.pos.entity.payment.Payment;
import com.example.pos.entity.projection.PaymentProjection;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

        @Query(nativeQuery = true , value = " select pp.payment_no  from pos_payment pp where payment_barcode = ?")
        String getInvoice(String paymentBarcode);

        @Query(nativeQuery = true, value = "select count(*) from pos_payment pp")
        int countRecord();

        @Query(nativeQuery = true , value = "select count(*) from pos_sale where sale_date = ?")
        int countSale(String currentData);

        @Query(nativeQuery = true, value = "select\r\n" + //
                                "\tps.total,\r\n" + //
                                "\tpp.receive_usd,\r\n" + //
                                "\tpp.receive_khr,\r\n" + //
                                "\tpp.change_usd,\r\n" + //
                                "\tpp.payment_barcode,\r\n" + //
                                "\tpp.change_khr,\r\n" + //
                                "\tpp.remaining_usd,\r\n" + //
                                "\tps.discount,\r\n" + //
                                "\tpp.remaining_khr,\r\n" + //
                                "\tpp.payment_no,\r\n" + //
                                "\tps.sale_date,\r\n" + //
                                "\tpct.name as customer_type,\r\n" + //
                                "\tpp.sale_id,pp.is_return,\r\n" + //
                                "\tpu.id as user_id,\r\n" + //
                                "\tpu.full_name \r\n" + //
                                "from\r\n" + //
                                "\tpos_payment pp\r\n" + //
                                "inner join pos_sale ps on\r\n" + //
                                "\tps.id = pp.sale_id\r\n" + //
                                "inner join pos_customer_type pct on\r\n" + //
                                "\tpct.id = pp.customer_type_id\r\n" + //
                                "inner join pos_user pu on pu.id = ps.user_id \r\n" + //
                                "where\r\n" + //
                                "\tpp.payment_no = ? ")
        PaymentProjection getPaymentDataWithPaymentNo(String paymentNo);

        @Query(nativeQuery = true, value = "select\r\n" + //
                                "\tps.total,\r\n" + //
                                "\tpp.receive_usd,\r\n" + //
                                "\tpp.receive_khr,\r\n" + //
                                "\tpp.change_usd,\r\n" + //
                                "\tpp.payment_barcode,\r\n" + //
                                "\tpp.change_khr,\r\n" + //
                                "\tpp.remaining_usd,\r\n" + //
                                "\tpp.remaining_khr,\r\n" + //
                                "\tpp.payment_no,\r\n" + //
                                "\tps.sale_date,\r\n" + //
                                "\tps.discount,\r\n" + //
                                "\tpct.name as customer_type,\r\n" + //
                                "\tpp.sale_id,\r\n" + //
                                "\tpu.full_name , pp.is_return ,\r\n" + //
                                "\tpu.id as user_id\r\n" + //
                                "from\r\n" + //
                                "\tpos_payment pp\r\n" + //
                                "inner join pos_sale ps on\r\n" + //
                                "\tps.id = pp.sale_id\r\n" + //
                                "inner join pos_customer_type pct on\r\n" + //
                                "\tpct.id = pp.customer_type_id\r\n" + //
                                "inner join pos_user pu on pu.id = ps.user_id  \r\n" + //
                                "order by\r\n" + //
                                "\tpp.id desc\r\n" + //
                                "limit 1")
        PaymentProjection getPaymentDataWithoutPaymentNo();

 

        @Query(nativeQuery = true, value = "select pp.payment_no  from pos_sale ps\r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? order by pp.id asc limit 1")
        String getFirstPaymentNumber(int userId, String date);

        @Query(nativeQuery = true, value = "select pp.payment_no  from pos_sale ps\r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? order by pp.id desc limit 1")
        String getLastPaymentNumber(int userId, String date);


        @Query(nativeQuery = true , value = "select pp.* from pos_sale ps inner join  \r\n" + //
                        "pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where payment_no = ?")
        Optional<Payment> getDataPayment(String paymentNo);

}
