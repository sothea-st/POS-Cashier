package com.example.pos.connection1.projections.ReportImport;

import java.math.BigDecimal;

public interface ReportImportProjection {
     BigDecimal getCost();
     int getqty_old();
     BigDecimal getAmount();
     int getPro_id();
     String getImp_date();
     String getPro_name_en();
     String getFull_name();
     String getpro_image_name();

     // pp.pro_id ,
	// p.imp_date ,
	// pp2.pro_name_en,
	// pu.full_name

}
