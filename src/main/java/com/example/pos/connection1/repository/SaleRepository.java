package com.example.pos.connection1.repository;

import com.example.pos.connection1.entity.Sale;
import com.example.pos.connection1.entity.SaleDetail;
import com.example.pos.connection1.projections.ReportImport.ReportSaledProjection;
import com.example.pos.connection1.projections.ReportImport.ReportSaledResponse;
import com.example.pos.connection1.projections.discountProjection.DiscountProjection;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {


//        Page<Sale> findByProduct


        @Query(nativeQuery = true, value = "select * from search_report_sale(?,?,?,?,?,?)")
        List<ReportSaledProjection> searchReportSale(String dateFrom,
                                                     String dateTo,
                                                      String searchValue , Integer userId , Integer pageSize , Integer pageNumber );

        @Query(nativeQuery = true, value = "SELECT * FROM get_sales_data_with_pagination(?,?, ?, ?, ?)")
        List<ReportSaledProjection> getReportSaleds(LocalDate dateFrom, LocalDate dateTo, Integer userId , Integer pageNumber , Integer pageSize);

        // @Query(nativeQuery = true, value = "SELECT * FROM get_sales_data(?,?, ?)")
        // List<ReportSaledProjection> getReportSaleds(LocalDate dateFrom, LocalDate dateTo, Integer userId);

        @Query(nativeQuery = true , value = "SELECT * FROM public.get_sales_data_count(?,?, ?)")
        int countSalesData(LocalDate dateFrom, LocalDate dateTo, Integer userId);

        @Query(nativeQuery = true, value = "select\r\n" + //
                        "\tpsd.qty,\r\n" + //
                        "\tpp.cost,\r\n" + //
                        "\tpsd.price,\r\n" + //
                        "\tpsd.amount,\r\n" + //
                        "\tpsd.discount as discount_percentage,\r\n" + //
                        "\tpp.pro_name_en,\r\n" + //
                        "\tpp.pro_image_name,\r\n" + //
                        "\tps.sale_date,\r\n" + //
                        "\tppt.tax_name,\r\n" + //
                        "\tps.discount_case,\r\n" + //
                        "\tps.discount,\r\n" + //
                        "\tpu.full_name\r\n" + //
                        "from\r\n" + //
                        "\tpos_sale ps\r\n" + //
                        "right join pos_sale_details psd on\r\n" + //
                        "\tpsd.sale_id = ps.id\r\n" + //
                        "inner join pos_product pp on\r\n" + //
                        "\tpp.id = psd.pro_id\r\n" + //
                        "inner join pos_product_tax ppt on\r\n" + //
                        "\tppt.id = pp.tax_id\r\n" + //
                        "inner join pos_user pu on\r\n" + //
                        "\tpu.id = ps.user_id\r\n" + //
                        "where\r\n" + //
                        "\tps.sale_date = ?\r\n" + //
                        "\tand \r\n" + //
                        "       ps.user_id = ?\r\n" + //
                        "        ")
        List<ReportSaledProjection> getReportSaleInToday(String date, int uesrId);

        @Query(nativeQuery = true, value = "  select sum(psd.qty) from pos_sale ps \r\n" + //
                        "        inner join pos_payment pp on pp.sale_id = ps.id\r\n" + //
                        "        inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "        inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "        where ps.user_id = ? and sale_date = ? and pp.receive_usd > 0\r\n" + //
                        "        and pp.payment_type = 'cash' and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String sumQtySaledByUsd(int empId, String date, String posId, String openDate, String userCode);

        @Query(nativeQuery = true, value = "select sum( ps.total ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id  \r\n" + //
                        "where ps.user_id = ? and sale_date = ?\r\n" + //
                        "and pp.receive_usd > 0 and pp.payment_type = 'cash'\r\n" + //
                        "and pos.pos_id = ? and pos.open_date = ?")
        String sumAmountSaledByUsd(int empId, String date, String posId, String openDate);

        @Query(nativeQuery = true, value = " select sum( psd.qty ) from pos_sale ps \r\n" + //
                        "    inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "    inner join pos_sale_details psd on psd.sale_id = pp.sale_id  \r\n" + //
                        "    inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "    where ps.user_id = ? and sale_date = ? and pp.receive_khr != '0' \r\n" + //
                        "    and pp.payment_type = 'cash'\r\n" + //
                        "    and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String sumQtySaledByKhr(int empId, String date, String posId, String openDate, String userCode);

        @Query(nativeQuery = true, value = "select sum(ps.total)  from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id  = ? and ps.sale_date = ? \r\n" + //
                        "and pp.payment_type = 'cash' and pp.receive_khr  > 0\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ?")
        String sumAmountSaledByKhr(int empId, String date, String posId, String openDate);

        // ================================================================================

        @Query(nativeQuery = true, value = "select sum( psd.qty ) from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id  \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id  \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.receive_usd >0\r\n" + //
                        "and pp.payment_type = 'aba'\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String totalCountQtyABA(int userId, String date, String posId, String openDate, String userCode);

        @Query(nativeQuery = true, value = " select sum( pp.receive_usd ) from pos_sale ps  \r\n" + //
                        "    inner join pos_payment pp on pp.sale_id = ps.id  \r\n" + //
                        "    inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "    where ps.user_id = ? and sale_date = ? and \r\n" + //
                        "    pp.receive_usd > 0 and pp.payment_type = 'aba'\r\n" + //
                        "    and  pos.pos_id = ? and pos.open_date = ?")
        String totalAmountABA(int userId, String date, String posId, String openDate);

        @Query(nativeQuery = true, value = "select sum(psd.qty)  from pos_sale ps  \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id  \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id  \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? \r\n" + //
                        "and pp.payment_type = 'mnk' \r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String totalCountQtyMNK(int userId, String date, String posId, String openDate, String userCode);

        @Query(nativeQuery = true, value = "select sum(ps.total)  from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id  \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id  \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ?  \r\n" + //
                        "and pp.payment_type = 'mnk' and pp.receive_usd > 0\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ?")
        String totalAmountMNK(int userId, String date, String posId, String openDate);

        @Query(nativeQuery = true, value = "select sum( psd.qty ) from pos_sale ps  \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id  \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id  \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and sale_date = ? and pp.payment_type = 'express'\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String totalCountQtyExpress(int userId, String date, String posId, String openDate, String userCode);

        @Query(nativeQuery = true, value = "select sum(ps.total)  from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id\r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? \r\n" + //
                        "and pp.payment_type = 'express' and pp.receive_usd > 0\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ?")
        String totalAmountExpress(int userId, String date, String posId, String openDate);

        @Query(nativeQuery = true, value = "select sum(psd.qty)  from pos_sale ps\r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ? and pp.payment_type = 'credit'\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ? and pos.user_code = ?")
        String totalCountQtyCredit(int userId, String date, String posId, String openDate, String userCode);

        @Query(nativeQuery = true, value = "select sum(ps.total)  from pos_sale ps \r\n" + //
                        "inner join pos_payment pp on pp.sale_id = ps.id  \r\n" + //
                        "inner join pos_sale_details psd on psd.sale_id = pp.sale_id \r\n" + //
                        "inner join pos_open_shift pos on pos.pos_id = ps.pos_id \r\n" + //
                        "where ps.user_id = ? and ps.sale_date = ?\r\n" + //
                        "and pp.payment_type = 'credit' and pp.receive_usd > 0\r\n" + //
                        "and  pos.pos_id = ? and pos.open_date = ?")
        String totalAmountCredit(int userId, String date, String posId, String openDate);

        @Query(nativeQuery = true, value = "SELECT\r\n" + //
                        "    psd.sale_id\r\n" + //
                        "FROM\r\n" + //
                        "    pos_sale ps\r\n" + //
                        "INNER JOIN\r\n" + //
                        "    pos_sale_details psd ON psd.sale_id = ps.id\r\n" + //
                        "INNER JOIN\r\n" + //
                        "    pos_payment pp ON pp.sale_id = ps.id\r\n" + //
                        "WHERE\r\n" + //
                        "    ps.active = 'Active'\r\n" + //
                        "    AND pp.receive_khr != '0'\r\n" + //
                        "    AND ps.user_id = ?\r\n" + //
                        "    AND ps.sale_date = ?\r\n" + //
                        "    AND ps.pos_id = ?\r\n" + //
                        "    AND pp.payment_type = 'cash'\r\n" + //
                        "GROUP BY\r\n" + //
                        "    psd.sale_id")
        List<Integer> countSaledNumKhr(int userId, String saleDate, String posId);
        // ======= old ========

        @Query(nativeQuery = true, value = "\t\r\n" + //
                        "select\r\n" + //
                        "\t psd.discount , psd.price , psd.qty , psd.qty_returned , psd.discount_type  \r\n" + //
                        "from\r\n" + //
                        "\tpos_sale ps\r\n" + //
                        "inner join pos_sale_details psd on\r\n" + //
                        "\tpsd.sale_id = ps.id\r\n" + //
                        "inner join pos_payment pp on\r\n" + //
                        "\tpp.sale_id = ps.id\r\n" + //
                        "where\r\n" + //
                        "\tps.active = 'Active'\r\n" + //
                        "\tand pp.receive_khr != '0'\r\n" + //
                        "\tand ps.user_id = ?\r\n" + //
                        "\tand ps.sale_date = ?\r\n" + //
                        "\tand ps.pos_id = ?\r\n" + //
                        "\tand pp.payment_type = 'cash'\r\n" + //
                        "\t")
        List<DiscountProjection> countSaledCashKhr(int userId, String saleDate, String posId);

        // ================= old =======================
        // @Query(nativeQuery = true, value = "select count(pp.*) from pos_sale ps \r\n"
        // + //
        // " inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
        // " where ps.user_id = ? and sale_date = ? and pp.payment_type = 'cash' \r\n" +
        // //
        // " and ps.pos_id = ? and pp.receive_usd > 0 and ps.active = 'Active' and
        // ps.sale_is_return is null")
        @Query(nativeQuery = true, value = "SELECT\r\n" + //
                        "    psd.sale_id\r\n" + //
                        "FROM\r\n" + //
                        "    pos_sale ps\r\n" + //
                        "INNER JOIN\r\n" + //
                        "    pos_sale_details psd ON psd.sale_id = ps.id\r\n" + //
                        "INNER JOIN\r\n" + //
                        "    pos_payment pp ON pp.sale_id = ps.id\r\n" + //
                        "WHERE\r\n" + //
                        "    ps.active = 'Active'\r\n" + //
                        "    AND pp.receive_usd > 0\r\n" + //
                        "    AND ps.user_id = ?\r\n" + //
                        "    AND ps.sale_date = ?\r\n" + //
                        "    AND ps.pos_id = ?\r\n" + //
                        "    AND pp.payment_type = 'cash'\r\n" + //
                        "GROUP BY\r\n" + //
                        "    psd.sale_id")
        List<Integer> countSaledNumUsd(int userId, String saleDate, String posId);

        @Query(nativeQuery = true, value = "\t\r\n" + //
                        "select\r\n" + //
                        "\t psd.discount , psd.price , psd.qty , psd.qty_returned , psd.discount_type  \r\n" + //
                        "from\r\n" + //
                        "\tpos_sale ps\r\n" + //
                        "inner join pos_sale_details psd on\r\n" + //
                        "\tpsd.sale_id = ps.id\r\n" + //
                        "inner join pos_payment pp on\r\n" + //
                        "\tpp.sale_id = ps.id\r\n" + //
                        "where\r\n" + //
                        "\tps.active = 'Active'\r\n" + //
                        "\tand pp.receive_usd > 0\r\n" + //
                        "\tand psd.is_returned is null\r\n" + //
                        "\tand ps.user_id = ?\r\n" + //
                        "\tand ps.sale_date = ?\r\n" + //
                        "\tand ps.pos_id = ?\r\n" + //
                        "\tand pp.payment_type = 'cash'\r\n" + //
                        "\t")
        List<DiscountProjection> countSaledUsd(int userId, String saleDate, String posId);

        @Query(nativeQuery = true, value = "select count(pp.*) from pos_sale ps \r\n" + //
                        " inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        " where ps.user_id = ? and sale_date = ? and pp.payment_type = 'mnk' \r\n" + //
                        " and ps.pos_id = ? and pp.receive_usd > 0 and ps.active = 'Active' and ps.sale_is_return is null")
        int countSaledNumMnk(int userId, String saleDate, String posId);

        @Query(nativeQuery = true, value = "select sum(ps.total) from pos_sale ps \r\n" + //
                        " inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        " where ps.user_id = ? and sale_date = ? and pp.payment_type = 'mnk' \r\n" + //
                        " and ps.pos_id = ? and pp.receive_usd > 0 and ps.active = 'Active' and ps.sale_is_return is null")
        Double countSaledMnk(int userId, String saleDate, String posId);

        @Query(nativeQuery = true, value = "select count(pp.*) from pos_sale ps \r\n" + //
                        " inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        " where ps.user_id = ? and sale_date = ? and pp.payment_type = 'aba' \r\n" + //
                        " and ps.pos_id = ? and pp.receive_usd > 0 and ps.active = 'Active' and ps.sale_is_return is null")
        int countSaledNumAba(int userId, String saleDate, String posId);

        @Query(nativeQuery = true, value = "select  sum(ps.total) from pos_sale ps \r\n" + //
                        " inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        " where ps.user_id = ? and sale_date = ? and pp.payment_type = 'aba' \r\n" + //
                        " and ps.pos_id = ? and pp.receive_usd > 0 and ps.active = 'Active' and ps.sale_is_return is null")
        Double countSaledAba(int userId, String saleDate, String posId);

        @Query(nativeQuery = true, value = "select count(pp.*) from pos_sale ps \r\n" + //
                        " inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        " where ps.user_id = ? and sale_date = ? and pp.payment_type = 'credit' \r\n" + //
                        " and ps.pos_id = ? and pp.receive_usd > 0 and ps.active = 'Active' and ps.sale_is_return is null")
        int countSaledNumCredit(int userId, String saleDate, String posId);

        @Query(nativeQuery = true, value = "select sum(ps.total) from pos_sale ps \r\n" + //
                        " inner join pos_payment pp on pp.sale_id = ps.id \r\n" + //
                        " where ps.user_id = ? and sale_date = ? and pp.payment_type = 'credit' \r\n" + //
                        " and ps.pos_id = ? and pp.receive_usd > 0 and ps.active = 'Active' and ps.sale_is_return is null")
        Double countSaledCredit(int userId, String saleDate, String posId);

}
