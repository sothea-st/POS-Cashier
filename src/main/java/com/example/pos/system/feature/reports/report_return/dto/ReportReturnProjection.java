package com.example.pos.system.feature.reports.report_return.dto;

import java.math.BigDecimal;

public interface ReportReturnProjection {
    String getInvoice_no();
    String getDate();
    String getProduct_name();

    Integer getQty();

    BigDecimal getPrice();

    BigDecimal getDiscount();

    BigDecimal getCost();

    String getReason();

    String getStaff();

    String getChoices();
}
