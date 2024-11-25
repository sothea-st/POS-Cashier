package com.example.pos.system.layer.projections.ReportImport;

import java.math.BigDecimal;

public interface ReportImportProjection {
     BigDecimal getCost();
     int getqty_old();
     BigDecimal getAmount();
     int getPro_id();
     String getImp_date();
     String getPro_name_en();
     String getBarcode();
     String getFull_name();
     String getpro_image_name();
     BigDecimal getTotal();
     BigDecimal getDiscount();
}
