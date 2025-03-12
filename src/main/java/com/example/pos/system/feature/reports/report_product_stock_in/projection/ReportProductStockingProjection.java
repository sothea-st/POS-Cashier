package com.example.pos.system.feature.reports.report_product_stock_in.projection;

import java.math.BigDecimal;

public interface ReportProductStockingProjection {
    String getProduct_name();
    String getCategory_name();
    String getSupplier_name();
    BigDecimal getPrice();
    Integer getQty();
    String getDate();
    String getDescription();
}
