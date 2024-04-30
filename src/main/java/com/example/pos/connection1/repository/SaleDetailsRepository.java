package com.example.pos.connection1.repository;

import com.example.pos.connection1.entity.SaleDetail;
import com.example.pos.connection1.entity.projection.SaleDetailProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SaleDetailsRepository extends JpaRepository<SaleDetail, Integer> {

        Optional<SaleDetail> findBySaleIdAndProductId(int saleId, int proId);

        @Query(nativeQuery = true, value = "select psd.price,psd.qty,pp.pro_name_en,pp.barcode from pos_sale ps \r\n" + //
                        " inner join pos_sale_details psd on psd.sale_id = ps.id\r\n" + //
                        " inner join pos_product pp on pp.id = psd.pro_id\r\n" + //
                        " where ps.user_id = ? and psd.sale_id = ? and psd.pro_id = ?  and psd.is_returned = 'returned' ")
        SaleDetailProjection getDataDetailReturn(int userId, int saleId,int productId);

        @Query(nativeQuery = true, value = "select psd.price,psd.qty,pp.pro_name_en,pp.barcode from pos_sale ps \r\n" + //
                        " inner join pos_sale_details psd on psd.sale_id = ps.id\r\n" + //
                        " inner join pos_product pp on pp.id = psd.pro_id\r\n" + //
                        " where ps.user_id = ? and psd.sale_id = ?  ")
        List<SaleDetailProjection> getDataDetail(int userId, int saleId);

        @Query(nativeQuery = true, value = "select sum( ( ( pp.price * psd.discount  )/100 )*psd.qty  )   from pos_sale ps\r\n"
                        + //
                        "inner join pos_sale_details psd on psd.sale_id = ps.id \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "inner join pos_product pp on pp.id = psd.pro_id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? and psd.discount = ?\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String totalAmount(int userId, String date, int discount, String posId, String openDate, String userCode);

        @Query(nativeQuery = true, value = "select count(ps.*)  from pos_sale ps " +
                        " inner join pos_sale_details psd on psd.sale_id  = ps.id  " +
                        " where ps.sale_date = ? and ps.pos_id = ? " +
                        " and psd.discount_type = 'percent' and psd.discount = ? group by ps.id")
        List<Integer> totalQty(String date, String posId, Double discount);

        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\tcount( pp.*)\r\n" + //
                        "from\r\n" + //
                        "\tpos_sale ps\r\n" + //
                        "inner join pos_open_shift pos on\r\n" + //
                        "\tpos.pos_id = ps.pos_id\r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id\r\n" + //
                        "where\r\n" + //
                        "\tps.user_id = ?\r\n" + //
                        "\tand ps.sale_date = ?\r\n" + //
                        "\tand pos.pos_id = ?\r\n" + //
                        "\tand pos.open_date = ?\r\n" + //
                        "\tand pos.user_code = ?\r\n" + //
                        " \tand pp.discount_type = 'dollar'\r\n" + //
                        "\t\t ")
        String totalQtyDollar(int userId, String date, String posId, String openDate, String userCode);

        @Query(nativeQuery = true, value = "select sum(psd.discount) from pos_sale ps \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = ps.id \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id  \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? \r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ? \r\n" + //
                        "and pos.user_code = ? and psd.discount_type = 'dollar'")
        String totalSaledDollar(int userId, String date, String posId, String openDate, String userCode);

        @Query(nativeQuery = true, value = "select sum(psd2.qty) from pos_sale ps2  \r\n" + //
                        "    inner join pos_sale_details psd2 on ps2.id = psd2.sale_id\r\n" + //
                        "    inner join pos_open_shift pos on pos.pos_id = ps2.pos_id \r\n" + //
                        "    where ps2.user_id = ? and ps2.sale_date = ?\r\n" + //
                        "    and pos.pos_id = ? and pos.open_date = ?  and pos.user_code = ? ")
        String totalQtySale(int userId, String date, String posId, String openDate, String userCode);

        @Query(nativeQuery = true, value = "select sum(ps.sub_total) from pos_sale ps\r\n" + //
                        "    inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "    where ps.user_id = ? and ps.sale_date = ? and \r\n" + //
                        "    pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String totalAmount(int userId, String date, String posId, String openDate, String userCode);

        @Query(nativeQuery = true, value = "\tselect count(ps.discount ) from pos_sale ps \r\n" + //
                        "\twhere\r\n" + //
                        "\tps.sale_date = ?\r\n" + //
                        "\tand ps.pos_id = ?\r\n" + //
                        "\tand ps.user_code = ? \r\n" + //
                        "\tand ps.discount > 0\r\n" + //
                        "\t\r\n")
        int totalQtyDiscount(String date, String posId, String userCode);

        @Query(nativeQuery = true, value = "\tselect sum(ps.discount ) from pos_sale ps \r\n" + //
                        "\twhere\r\n" + //
                        "\tps.sale_date = ?\r\n" + //
                        "\tand ps.pos_id = ?\r\n" + //
                        "\tand ps.user_code = ? \r\n" + //
                        "\tand ps.discount > 0\r\n" + //
                        "\t\r\n")
        String totalAmountDiscount(String date, String posId, String userCode);

        @Query(nativeQuery = true, value = "select sum(prd.retur_qty)  from pos_sale ps\r\n" + //
                        "        inner join pos_payment pp on pp.sale_id = ps.id\r\n" + //
                        "        inner join pos_return_product prp on prp.payment_no = pp.payment_no \r\n" + //
                        "        inner join pos_return_details prd on prd.return_id = prp.id \r\n" + //
                        "        inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "        where ps.user_id= ? and ps.sale_date = ? and pp.is_return = 'returned' and \r\n" + //
                        "        pos.pos_id = ? and pos.open_date = ?")
        String totalReturnQty(int userId, String date, String posId, String openDate);

        @Query(nativeQuery = true, value = "\tselect sum(ps.total)  from pos_sale ps \r\n" + //
                        "\twhere\r\n" + //
                        "\tps.sale_date = ?\r\n" + //
                        "\tand ps.pos_id = ?\r\n" + //
                        "\tand ps.user_code = ? \r\n" + //
                        "\tand ps.sale_is_return = 'returned'")
        Double totalReturnAmountDiscount(String date, String posId, String userCode);

        // ============================================ new
        // ==============================================

        @Query(nativeQuery = true, value = "\tselect count(ps.*) from pos_sale ps \r\n" + //
                        "\twhere\r\n" + //
                        "\tps.sale_date = ?\r\n" + //
                        "\tand ps.pos_id = ?\r\n" + //
                        "\tand ps.user_code = ? \r\n" + //
                        "\tand ps.sale_is_return = 'returned'")
        int numRetured(String saleDate, String posId, String userCode);

        @Query(nativeQuery = true, value = "\tselect count(ps.*) from pos_sale ps \r\n" + //
                        "\twhere\r\n" + //
                        "\tps.sale_date = ?\r\n" + //
                        "\tand ps.pos_id = ?\r\n" + //
                        "\tand ps.user_code = ? \r\n" + //
                        "\t")
        int numOfSale(String currentDate, String posId, String userCode);

        @Query(nativeQuery = true, value = "\tselect sum(ps.sub_total)  from pos_sale ps \r\n" + //
                        "\twhere\r\n" + //
                        "\tps.sale_date = ?\r\n" + //
                        "\tand ps.pos_id = ?\r\n" + //
                        "\tand ps.user_code = ? \r\n" + //
                        "\t")
        Double totalSaledAmount(String currentDate, String posId, String userCode);

        @Query(nativeQuery = true, value = "SELECT trunc( sum(((psd.price*psd.qty)/1.1)*0.1), 2) as vat\r\n" + //
                        "FROM pos_sale ps\r\n" + //
                        "INNER JOIN pos_sale_details psd ON ps.id = psd.sale_id\r\n" + //
                        "INNER JOIN pos_product pp ON psd.pro_id = pp.id\r\n" + //
                        "INNER JOIN pos_product_tax ppt ON ppt.id = pp.tax_id\r\n" + //
                        "WHERE ps.sale_date = ? AND ps.pos_id = ? AND ps.user_code = ? AND ppt.rate_tax > 0\r\n" + //
                        "")
        Double vat10(String currentDate, String posId, String userCode);

        @Query(nativeQuery = true, value = "SELECT trunc( sum(((  ((psd.price*psd.qty)/1.1) /1.006))*0.2*0.03),2) as vat\r\n"
                        + //
                        "FROM pos_sale ps\r\n" + //
                        "INNER JOIN pos_sale_details psd ON ps.id = psd.sale_id\r\n" + //
                        "INNER JOIN pos_product pp ON psd.pro_id = pp.id\r\n" + //
                        "INNER JOIN pos_product_tax ppt ON ppt.id = pp.tax_id\r\n" + //
                        "WHERE ps.sale_date = ? AND ps.pos_id = ? AND ps.user_code = ? AND ppt.rate_tax = 3\r\n" + //
                        "")
        Double vat3(String currentDate, String posId, String userCode);

        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\t trunc(sum((psd.price*psd.qty)/1.1),2) as vat\r\n" + //
                        "from\r\n" + //
                        "\tpos_sale ps\r\n" + //
                        "inner join pos_sale_details psd on\r\n" + //
                        "\tps.id = psd.sale_id\r\n" + //
                        "inner join pos_product pp on\r\n" + //
                        "\tpsd.pro_id = pp.id\r\n" + //
                        "inner join pos_product_tax ppt on\r\n" + //
                        "\tppt.id = pp.tax_id\r\n" + //
                        "inner join pos_payment pp2 on\r\n" + //
                        "\tpp2.sale_id = psd.sale_id\r\n" + //
                        "inner join pos_product pp3 on\r\n" + //
                        "\tpp3.id = psd.pro_id\r\n" + //
                        "where\r\n" + //
                        "\tps.sale_date = ?\r\n" + //
                        "\tand ps.pos_id = ?\r\n" + //
                        "\tand ps.user_code = ?\r\n" + //
                        "\tand ppt.rate_tax > 0")
        Double vat(String currentDate, String posId, String userCode);

        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\ttrunc( sum((psd.price* psd.qty) / (1+ (ppt.rate_tax/100))  ) ,2 ) as vat\r\n" + //
                        "from\r\n" + //
                        "\tpos_sale ps\r\n" + //
                        "inner join pos_sale_details psd on\r\n" + //
                        "\tps.id = psd.sale_id\r\n" + //
                        "inner join pos_product pp on\r\n" + //
                        "\tpsd.pro_id = pp.id\r\n" + //
                        "inner join pos_product_tax ppt on\r\n" + //
                        "\tppt.id = pp.tax_id\r\n" + //
                        "inner join pos_payment pp2 on\r\n" + //
                        "\tpp2.sale_id = psd.sale_id\r\n" + //
                        "inner join pos_product pp3 on\r\n" + //
                        "\tpp3.id = psd.pro_id\r\n" + //
                        "where\r\n" + //
                        "\tps.sale_date = ?\r\n" + //
                        "\tand ps.pos_id = ?\r\n" + //
                        "\tand ps.user_code = ?\r\n" + //
                        "\tand ppt.rate_tax = 0 ") // for
                                                   // Non-Vat
        Double noneVat(String currentDate, String posId, String userCode);

        @Query(nativeQuery = true, value = "SELECT TRUNC(SUM((psd.amount / (1 + (ppt.rate_tax / 100)))), 2) AS vat\r\n"
                        + //
                        "FROM pos_sale ps\r\n" + //
                        "INNER JOIN pos_sale_details psd ON ps.id = psd.sale_id\r\n" + //
                        "INNER JOIN pos_product pp ON psd.pro_id = pp.id\r\n" + //
                        "INNER JOIN pos_product_tax ppt ON ppt.id = pp.tax_id\r\n" + //
                        "WHERE ps.sale_date = ? AND ps.pos_id = ? AND ps.user_code = ? and  pp.tax_id  = 1 ") // for VAT
                                                                                                              // state
                                                                                                              // charge
        Double vatStateCharge(String currentDate, String posId, String userCode);

        @Query(nativeQuery = true, value = "SELECT  trunc(sum((((psd.price*psd.qty)/1.1)/1.006)*0.2),2) as vat\r\n" + //
                        "FROM pos_sale ps\r\n" + //
                        "INNER JOIN pos_sale_details psd ON ps.id = psd.sale_id\r\n" + //
                        "INNER JOIN pos_product pp ON psd.pro_id = pp.id\r\n" + //
                        "INNER JOIN pos_product_tax ppt ON ppt.id = pp.tax_id\r\n" + //
                        "WHERE ps.sale_date = ? AND ps.pos_id = ? AND ps.user_code = ? and  pp.tax_id  = 4 ") // for plt
        Double plt(String currentDate, String posId, String userCode);

}
