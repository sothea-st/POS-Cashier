package com.example.pos.system.feature.order_online;

import com.example.pos.system.domain.order_online.OrderOnline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface OrderOnlineRepository extends JpaRepository<OrderOnline,Integer> {


    Optional<OrderOnline> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    @Query("Select count(o) from OrderOnline o " +
            "where o.status = true and o.isDeleted = false " +
            "and o.accept = :accept")
    long countByOrderStatus(
            @Param("accept") String accept
    );

    @Query("Select sum(o.totalAmount) from OrderOnline o " +
            "where o.status = true and o.isDeleted = false " +
            "and o.accept = :accept")
    BigDecimal subByTotalAmount(
            @Param("accept") String accept
    );


    @Query(nativeQuery = true,value = "select count(*) from pos_order_onlines poo where order_date = :date ")
    long countOrdersToday(@Param("date") LocalDate date);


    Page<OrderOnline> findByStatusTrueAndIsDeletedFalseAndOrderDateBetween(
            LocalDate dateFrom,
            LocalDate dateTo,
            PageRequest pageRequest
    );


    @Query("SELECT o FROM OrderOnline o " +
            "WHERE o.status = true " +
            "AND o.isDeleted = false " +
            "AND o.orderDate BETWEEN :dateFrom AND :dateTo " +
            "AND (LOWER(o.customerName) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(o.orderNumber) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<OrderOnline> searchByCustomerNameAndOrderNumber(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("search") String search,
            PageRequest pageRequest);

    @Query("SELECT o FROM OrderOnline o " +
            "WHERE o.status = true " +
            "AND o.isDeleted = false " +
            "AND o.orderDate BETWEEN :dateFrom AND :dateTo " +
            "AND o.orderStatus = :orderStatus " +
            "AND (LOWER(o.customerName) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(o.orderNumber) LIKE LOWER(CONCAT('%', :search, '%')))"
    )
    Page<OrderOnline> searchByOrderStatus(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("search") String search,
            @Param("orderStatus") String orderStatus,
            PageRequest pageRequest);


    @Query("SELECT o FROM OrderOnline o " +
            "WHERE o.status = true " +
            "AND o.isDeleted = false " +
            "AND o.orderDate BETWEEN :dateFrom AND :dateTo " +
            "AND o.paymentStatus = :paymentStatus " +  // Added space here after the equal sign
            "AND (LOWER(o.customerName) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(o.orderNumber) LIKE LOWER(CONCAT('%', :search, '%')))"
    )
    Page<OrderOnline> searchByPaymentStatus(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("search") String search,
            @Param("paymentStatus") String paymentStatus,
            PageRequest pageRequest);

    @Query("SELECT o FROM OrderOnline o " +
            "WHERE o.status = true " +
            "AND o.isDeleted = false " +
            "AND o.orderDate BETWEEN :dateFrom AND :dateTo " +
            "AND o.orderStatus = :orderStatus " +  // Added space here after the equal sign
            "AND o.paymentStatus = :paymentStatus " +  // Added space here after the equal sign
            "AND (LOWER(o.customerName) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(o.orderNumber) LIKE LOWER(CONCAT('%', :search, '%')))"
    )
    Page<OrderOnline> searchByPaymentStatusAndOrderStatus(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("search") String search,
            @Param("orderStatus") String orderStatus,
            @Param("paymentStatus") String paymentStatus,
            PageRequest pageRequest);



    Page<OrderOnline> findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndOrderStatus(
            LocalDate dateFrom,
            LocalDate dateTo,
            String orderStatus,
            PageRequest pageRequest
    );

    Page<OrderOnline> findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndPaymentStatus(
            LocalDate dateFrom,
            LocalDate dateTo,
            String paymentStatus,
            PageRequest pageRequest
    );

    Page<OrderOnline> findByStatusTrueAndIsDeletedFalseAndOrderDateBetweenAndOrderStatusAndPaymentStatus(
            LocalDate dateFrom,
            LocalDate dateTo,
            String orderStatus,
            String paymentStatus,
            PageRequest pageRequest
    );


    @Query("SELECT count(o) FROM OrderOnline o " +
            "WHERE o.status = true " +
            "AND o.isDeleted = false " +
            "AND o.orderStatus = :orderStatus")
    long countOrderStatusNew(@Param("orderStatus") String orderStatus);

    @Query("SELECT SUM(o.grandTotal) FROM OrderOnline o " +
            "WHERE o.status = true " +
            "AND o.isDeleted = false " +
            "AND o.orderStatus = :orderStatus")
    BigDecimal sumGrandTotalByOrderStatus(@Param("orderStatus") String orderStatus);


}
