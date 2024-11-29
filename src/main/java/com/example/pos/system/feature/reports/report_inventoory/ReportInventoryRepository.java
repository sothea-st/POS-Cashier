package com.example.pos.system.feature.reports.report_inventoory;

import com.example.pos.system.domain.Product;
import com.example.pos.system.domain.report.ReportInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportInventoryRepository extends JpaRepository<ReportInventory,Long> {

    // Fetch the latest endingQty for a given product
    @Query(nativeQuery = true, value = "SELECT r.ending_qty  FROM pos_report_inventories  r WHERE r.product_id  = ? ORDER BY r.id desc limit 1")
    Integer findTopEndingQtyByProductOrderByDateDesc(@Param("product_id") Integer productID);

}
