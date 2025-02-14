package com.example.pos.system.feature.reports.report_adjustment.dto.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface DetailProjection {
    LocalDate getTransaction_date();
    String getTransaction();

    String getReference();

    String getReason();
    String getReturn_type();
    Integer getQty();

    BigDecimal getTotal_cost();

}
