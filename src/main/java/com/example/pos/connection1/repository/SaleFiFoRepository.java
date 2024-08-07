package com.example.pos.connection1.repository;

import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.entity.SaleFiFo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleFiFoRepository extends JpaRepository<SaleFiFo,Integer> {

    List<SaleFiFo> findByPaymentNoAndSaleQtyGreaterThanAndProductOrderByLocalDateDesc(String paymentNo,int saleQty,Product product);

    @Query(nativeQuery = true , value = "select\n" +
            "\tsum(psf.sale_qty)\n" +
            "from\n" +
            "\tpos_sale_fifo psf\n" +
            "where\n" +
            "\tpayment_no = ?\n" +
            "\tand product_id = ?\n" +
            "\tand sale_qty > 0")
    Integer countSaleQ(String paymentNo, Product product);

    SaleFiFo findByPaymentNoAndProduct(String paymentNo, Product product);


}
