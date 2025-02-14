package com.example.pos.system.feature.reports.report_adjustment.dto.projection;

public interface AdjustmentProjection {
    String getTransaction();
    Integer getId();

    Integer getQty();

    Integer getProduct_id();

    Integer getDivision_id();

    Integer getDepartment_id();

    String getPro_name_en();
}
