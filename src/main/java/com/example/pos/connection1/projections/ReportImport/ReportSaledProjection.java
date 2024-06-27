package com.example.pos.connection1.projections.ReportImport;

import java.math.BigDecimal;

public interface ReportSaledProjection {
     String getSale_date();
     String getPro_name_en();
     int getQty();
     BigDecimal getPrice();
     BigDecimal getAmount();
     String getTax_name();
     BigDecimal getCost();
     String getPro_image_name();
     String getDiscount_case();
     double getDiscount();
     double getdiscount_percentage();
     String getfull_name();
     String getBarcode();
}
