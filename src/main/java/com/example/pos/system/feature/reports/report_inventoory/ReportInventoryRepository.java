package com.example.pos.system.feature.reports.report_inventoory;

import com.example.pos.system.domain.report.ReportInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportInventoryRepository extends JpaRepository<ReportInventory,Long> {

}
