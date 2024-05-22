package com.example.pos.connection1.repository.paymentRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.example.pos.connection1.entity.models.PaymentModel;
import com.example.pos.connection1.entity.models.ProductModel;
import com.example.pos.connection1.entity.payment.Payment;
import com.example.pos.connection1.entity.projection.PaymentProjection;
import com.example.pos.connection1.repository.productProjection.ProductProjection;
import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {


        @Query(nativeQuery = true , value = "\r\n" + //
                                "select\r\n" + //
                                "\tps.id\r\n" + //
                                "from\r\n" + //
                                "\tpos_sale ps\r\n" + //
                                "inner join pos_payment pp on\r\n" + //
                                "\tpp.sale_id = ps.id\r\n" + //
                                "where\r\n" + //
                                "\tps.active = 'Active'\r\n" + //
                                "\tand pp.payment_no = ?")
        Integer getSaleId(String paymentNumber);


        @Query(nativeQuery = true , value = "select count(*) from pos_payment pp2 where payment_no = ?")
        int isExistInvoice(String invoiceNo);

        Payment  findByPaymentNo(String paymentNo);

        @Query(nativeQuery = true, value = " select pp.payment_no  from pos_payment pp where payment_barcode = ?")
        String getInvoice(String paymentBarcode);

        @Query(nativeQuery = true, value = "select count(*) from pos_payment pp where pos_id = ?")
        int countRecord(String posId);

        @Query(nativeQuery = true, value = "select count(*) from pos_sale where sale_date = ?")
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

        @Query(nativeQuery = true, value = "select\r\n" + //
                        "    pp.receive_usd,\r\n" + //
                        "    pp.receive_khr,\r\n" + //
                        "    pp.change_khr,\r\n" + //
                        "    pp.change_usd,\r\n" + //
                        "    ps.total\r\n" + //
                        "from\r\n" + //
                        "    pos_payment pp\r\n" + //
                        "inner join pos_sale ps \r\n" + //
                        "on pp.sale_id = ps.id\r\n" + //
                        "where\r\n" + //
                        "    pp.payment_no = ?")
        PaymentModel getSomeData(String invoiceNumber);

        @Query(nativeQuery = true, value = "select pp.payment_no  from pos_sale ps\r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? and ps.active = 'Active'  order by pp.id asc limit 1")
        String getFirstPaymentNumber(int userId, String date);

        @Query(nativeQuery = true, value = "select pp.payment_no  from pos_sale ps\r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? order by pp.id desc limit 1")
        String getLastPaymentNumber(int userId, String date);

        @Query(nativeQuery = true, value = "select pp.* from pos_sale ps inner join  \r\n" + //
                        "pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "where payment_no = ?")
        Optional<Payment> getDataPayment(String paymentNo);



        @Query(nativeQuery = true, value = "select\r\n" + //
                                "\tpc.id,\r\n" + //
                                "\tpc.barcode,\r\n" + //
                                "\tpc.cat_id ,\r\n" + //
                                "\tpc.brand_id ,\r\n" + //
                                "\tpc.flag ,\r\n" + //
                                "\tpc.weight ,\r\n" + //
                                "\tpc.pro_image_name ,\r\n" + //
                                "\tpc.brand_id ,\r\n" + //
                                "\tpc.pro_name_en ,\r\n" + //
                                "\tpc.pro_name_kh ,\r\n" + //
                                "\tpc.cost,\r\n" + //
                                "\tpc.price ,\r\n" + //
                                "\tpc.product_status ,\r\n" + //
                                "\tpc.discount ,\r\n" + //
                                "\tpc.code_out_stock ,\r\n" + //
                                "\tpc.code_expired\r\n" + //
                                "from\r\n" + //
                                "\tpos_payment pp\r\n" + //
                                "inner join pos_sale ps on\r\n" + //
                                "\tps.id = pp.sale_id\r\n" + //
                                "inner join pos_sale_details psd on\r\n" + //
                                "\tpsd.sale_id = ps.id\r\n" + //
                                "inner join pos_product pc on\r\n" + //
                                "\tpc.id = psd.pro_id\r\n" + //
                                "where\r\n" + //
                                "\tpp.payment_no = ?\r\n" + //
                                "\tand pc.barcode = ?\r\n" + //
                                "                        ")
        List<ProductProjection> getProductByBarcodeInInvoice(String invoice, String barcode);

}
