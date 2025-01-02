package com.example.pos.system.layer.repository.sourceDataRepository;

import com.example.pos.system.feature.reports.report_return.dto.ReportReturnProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pos.system.domain.sourceData.ReturnProduct;
import com.example.pos.system.layer.repository.productProjection.ProductProjection;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReturnProductRepository  extends JpaRepository<ReturnProduct,Integer>{
     @Query(nativeQuery = true , value = "select\r\n" + //
                    "\tid,\r\n" + //
                    "\tcat_id ,\r\n" + //
                    "\tbrand_id ,\r\n" + //
                    "\tflag ,\r\n" + //
                    "\tweight ,\r\n" + //
                    "\tpro_image_name ,\r\n" + //
                    "\tbarcode ,\r\n" + //
                    "\tpro_name_en ,\r\n" + //
                    "\tpro_name_kh ,\r\n" + //
                    "\tcost,\r\n" + //
                    "\tprice,\r\n" + //
                    "\tproduct_status ,\r\n" + //
                    "\tdiscount\r\n" + //
                    "from\r\n" + //
                    "\tpos_product pp\r\n" + //
                    "where\r\n" + //
                    "\tbarcode = ?")
     ProductProjection getProductByBarcode(String barcode);


     @Query(value = "SELECT " +
             "    r.payment_no AS invoice_no, " +
             "    r.return_date AS date, " +
             "    pp.pro_name_en AS product_name, " +
             "    p.retur_qty AS qty, " +
             "    p.return_price AS price, " +
             "    p.discount_amt AS discount, " +
             "    pp.cost, " +
             "    pr.reason, " +
             "    pu.full_name AS staff " +
             "FROM " +
             "    pos_return_product r " +
             "LEFT JOIN pos_return_details p " +
             "    ON r.id = p.return_id " +
             "INNER JOIN pos_product pp " +
             "    ON pp.id = p.return_pro_id " +
             "INNER JOIN pos_reason pr " +
             "    ON pr.id = r.reason_id " +
             "INNER JOIN pos_user pu " +
             "    ON pu.id = r.create_by " +
             "WHERE DATE(r.return_date) BETWEEN :dateFrom AND :dateTo " +
             "ORDER BY r.create_date DESC", nativeQuery = true)
     List<ReportReturnProjection> getReportReturns(
             @Param("dateFrom") LocalDate dateFrom,
             @Param("dateTo") LocalDate dateTo);


     @Query(value = "SELECT " +
             "    r.payment_no AS invoice_no, " +
             "    r.return_date AS date, " +
             "    pp.pro_name_en AS product_name, " +
             "    p.retur_qty AS qty, " +
             "    p.return_price AS price, " +
             "    p.discount_amt AS discount, " +
             "    pp.cost, " +
             "    pr.reason, " +
             "    pu.full_name AS staff " +
             "FROM " +
             "    pos_return_product r " +
             "LEFT JOIN pos_return_details p " +
             "    ON r.id = p.return_id " +
             "INNER JOIN pos_product pp " +
             "    ON pp.id = p.return_pro_id " +
             "INNER JOIN pos_reason pr " +
             "    ON pr.id = r.reason_id " +
             "INNER JOIN pos_user pu " +
             "    ON pu.id = r.create_by " +
             "WHERE DATE(r.return_date) BETWEEN :dateFrom AND :dateTo " + // Truncate time if necessary
             "ORDER BY r.create_date DESC " +
             "LIMIT :pageSize OFFSET :pageNumber", nativeQuery = true)
     List<ReportReturnProjection> getReportReturn(
             @Param("dateFrom") LocalDate dateFrom,
             @Param("dateTo") LocalDate dateTo,
             @Param("pageSize") Integer pageSize,
             @Param("pageNumber") Integer pageNumber);




     @Query(nativeQuery = true,value = "SELECT\n" +
             "     \n" +
             "\tcount(r.payment_no)\n" +
             "    \n" +
             "FROM\n" +
             "    pos_return_product r\n" +
             "LEFT JOIN pos_return_details p \n" +
             "    ON r.id = p.return_id\n" +
             "INNER JOIN pos_product pp \n" +
             "    ON pp.id = p.return_pro_id\n" +
             "INNER JOIN pos_reason pr \n" +
             "    ON pr.id = r.reason_id\n" +
             "INNER JOIN pos_user pu \n" +
             "    ON pu.id = r.create_by\n" +
             "where DATE(r.return_date) between :dateFrom and :dateTo")
     Integer getCountResult(@Param("dateFrom") LocalDate dateFrom , @Param("dateTo") LocalDate dateTo);

     @Query(value = "SELECT " +
             "    r.payment_no AS invoice_no, " +
             "    r.return_date AS date, " +
             "    pp.pro_name_en AS product_name, " +
             "    p.retur_qty AS qty, " +
             "    p.return_price AS price, " +
             "    p.discount_amt AS discount, " +
             "    pp.cost, " +
             "    pr.reason, " +
             "    pu.full_name AS staff " +
             "FROM " +
             "    pos_return_product r " +
             "LEFT JOIN pos_return_details p ON r.id = p.return_id " +
             "INNER JOIN pos_product pp ON pp.id = p.return_pro_id " +
             "INNER JOIN pos_reason pr ON pr.id = r.reason_id " +
             "INNER JOIN pos_user pu ON pu.id = r.create_by " +
             "WHERE " +
             "    DATE(r.return_date) BETWEEN :dateFrom AND :dateTo " +
             "    AND (r.payment_no ILIKE %:searchValue% OR pp.pro_name_en ILIKE %:searchValue%) " +
             "ORDER BY " +
             "    r.create_date DESC " , nativeQuery = true)
     List<ReportReturnProjection> searchReportReturn(@Param("dateFrom") LocalDate dateFrom,
                                                     @Param("dateTo") LocalDate dateTo,

                                                     @Param("searchValue") String searchValue);


     @Query(nativeQuery = true, value = "SELECT " +
             "    COUNT(r.payment_no) " +
             "FROM " +
             "    pos_return_product r " +
             "LEFT JOIN pos_return_details p ON r.id = p.return_id " +
             "INNER JOIN pos_product pp ON pp.id = p.return_pro_id " +
             "INNER JOIN pos_reason pr ON pr.id = r.reason_id " +
             "INNER JOIN pos_user pu ON pu.id = r.create_by " +
             "WHERE " +
             "    DATE(r.return_date) BETWEEN :dateFrom AND :dateTo " +
             "    AND (r.payment_no ILIKE %:search% OR pp.pro_name_en ILIKE %:search%)")
     Integer getCountResultSearch(@Param("dateFrom") LocalDate dateFrom,
                                  @Param("dateTo") LocalDate dateTo,
                                  @Param("search") String search);



}
