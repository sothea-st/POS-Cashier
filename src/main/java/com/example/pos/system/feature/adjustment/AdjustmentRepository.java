package com.example.pos.system.feature.adjustment;

import com.example.pos.system.domain.adjustment.Adjustment;
import com.example.pos.system.domain.sourceData.Reason;
import com.example.pos.system.feature.reports.report_adjustment.dto.projection.AdjustmentProjection;
import com.example.pos.system.feature.reports.report_adjustment.dto.projection.DetailProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AdjustmentRepository extends JpaRepository<Adjustment, Long> {


    @Query(nativeQuery = true ,
    value = "select\n" +
            "\tpa.transaction_date,\n" +
            "\tpa.transaction,\n" +
            "\tpa.reference,\n" +
            "\tpr.reason,\n" +
            "\tpr.return_type,\n" +
            "\tpad2.qty,\n" +
            "\tpa.total_cost\n" +
            "from\n" +
            "\tpos_adjustments pa\n" +
            "left join pos_adjustment_details pad2 \n" +
            "\ton\n" +
            "\tpad2.adjustment_id = pa.id\n" +
            "inner join pos_product pp on\n" +
            "\tpp.id = pad2.product_id\n" +
            "inner join pos_reason pr on\n" +
            "\tpr.id = pa.reason_id\n" +
            "where\n" +
            "\tpad2.product_id = :productId\n" +
            "\tand\n" +
            "\tpa.transaction_date between :dateFrom and :dateTo")
    List<DetailProjection> getDetail(
            @Param("productId") Integer productId,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo
            );

    Page<Adjustment> findByIsDeletedTrue(PageRequest pageRequest);

    Optional<Adjustment> findByIdAndIsDeletedTrue(Integer id);


    @Query(value = """
            select u from Adjustment u
            where u.isDeleted = true
             and u.transactionDate >= :dateFrom
            and u.transactionDate <= :dateTo
            and (lower(u.transaction) like lower(concat('%', :name, '%'))
            or lower(u.reference) like lower(concat('%', :name, '%')))
            """)
    Page<Adjustment> searchAdjustment(
            PageRequest pageRequest,
            @PathVariable("name") String name,
            @PathVariable("dateFrom") LocalDate dateFrom,
            @PathVariable("dateTo") LocalDate dateTo
    );


    @Query(value = """
            select u from Adjustment u
            where u.isDeleted = true
            and u.transactionDate >= :dateFrom
            and u.transactionDate <= :dateTo
            """)
    Page<Adjustment> filter(
            PageRequest pageable,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo
    );


    @Query(nativeQuery = true, value = """
            SELECT EXISTS (
                SELECT 1 FROM pos_adjustment_details pad2
                WHERE pad2.division_id = :divisionId
                AND pad2.department_id = :departmentId
            )
            """)
    Boolean isExist(@Param("divisionId") Integer divisionId, @Param("departmentId") Integer departmentId);

    @Query(nativeQuery = true, value = """
            SELECT EXISTS (
                SELECT 1 FROM pos_adjustment_details pad2
                WHERE pad2.product_id = :productId
            )
            """)
    Boolean isExistProduct(@Param("productId") Integer productId);



    @Query(nativeQuery = true,value = "" +
            "select\n" +
            "\tpa.\"transaction\" ,\n" +
            "\tpad2.id ,\n" +
            "\tpad2.qty ,\n" +
            "\tpad2.product_id ,\n" +
            "\tpad2.division_id ,\n" +
            "\tpad2.department_id," +
            "pp.pro_name_en\n" +
            "from\n" +
            "\tpos_adjustments pa\n" +
            "inner join pos_adjustment_details pad2 \n" +
            "on pad2.adjustment_id = pa.id \n" +
            " inner join pos_product pp on pp.id = pad2.product_id  \n" +
            "where\n" +
            "    pad2.division_id = :divisionId\n" +
            "\tand pad2.department_id  = :departmentId\n" +
            "\tand\n" +
            "\tpa.transaction_date between :dateFrom and :dateTo")
    List<AdjustmentProjection> listAdjustmentProjection(
            @Param("divisionId") Integer divisionId,
            @Param("departmentId") Integer departmentId,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo
    );


    @Query(value = """
            select u from Adjustment u
            where u.isDeleted = true
            and u.transactionDate >= :dateFrom
            and u.transactionDate <= :dateTo
            and (lower(u.transaction) like lower(concat('%', :value, '%')))
            """)
    Page<Adjustment> filter(
            PageRequest pageable,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("value") String value
    );

    @Query(value = """
            select u from Adjustment u
            where u.isDeleted = true
            and u.transactionDate >= :dateFrom
            and u.transactionDate <= :dateTo
            and u.status = :value
            """)
    Page<Adjustment> filterStatus(
            PageRequest pageable,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("value") String value
    );

    @Query(value = """
            select u from Adjustment u
            where u.isDeleted = true
            and u.transactionDate >= :dateFrom
            and u.transactionDate <= :dateTo
            and u.reason.id = :value
            """)
    Page<Adjustment> filterReason(
            PageRequest pageable,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("value") Integer value
    );


    @Query(value = """
            select u from Adjustment u
            where u.isDeleted = true
            and u.reason.id = :reasonId
            and u.transactionDate >= :dateFrom
            and u.transactionDate <= :dateTo
            and (lower(u.transaction) like lower(concat('%', :value, '%')))
            """)
    Page<Adjustment> filter(
            PageRequest pageable,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("value") String value,
            @Param("reasonId") Integer reasonId
    );

    @Query(value = """
            select u from Adjustment u
            where u.isDeleted = true
            and u.status = :status
            and u.transactionDate >= :dateFrom
            and u.transactionDate <= :dateTo
            and (lower(u.transaction) like lower(concat('%', :value, '%')))
            """)
    Page<Adjustment> filter(
            PageRequest pageable,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("value") String value,
            @Param("status") String status
    );


    @Query(value = """
            select u from Adjustment u
            where u.isDeleted = true
            and u.status = :status
            and u.reason.id = :reasonId
            and u.transactionDate >= :dateFrom
            and u.transactionDate <= :dateTo
            """)
    Page<Adjustment> filterStatus(
            PageRequest pageable,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("status") String status,
            @Param("reasonId") Integer reasonId   // Ensure this is of type Long in the query
    );

    @Query(value = """
            select u from Adjustment u
            where u.isDeleted = true
            and u.status = :status
            and u.reason.id = :reasonId
            and u.transactionDate >= :dateFrom
            and u.transactionDate <= :dateTo
            and (lower(u.transaction) like lower(concat('%', :value, '%')))
            """)
    Page<Adjustment> filter(
            PageRequest pageable,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("value") String value,
            @Param("status") String status,
            @Param("reasonId") Integer reasonId   // Ensure this is of type Long in the query
    );


}
