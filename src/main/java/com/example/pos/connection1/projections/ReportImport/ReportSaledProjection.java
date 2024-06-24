package com.example.pos.connection1.projections.ReportImport;

import java.math.BigDecimal;

public interface ReportSaledProjection {
     // psd.qty,
	// pp.cost,
	// psd.price,
	// psd.amount,
	// psd.discount,
	// pp.pro_name_en,
	// pp.pro_image_name,
	// ps.sale_date,
	// ppt.tax_name,
	// ps.discount_case

     int getQty();
     BigDecimal getCost();
     BigDecimal getPrice();
     String getPro_name_en();
     String getPro_image_name();
     String getSale_date();
     String getTax_name();
     String getDiscount_case();
     BigDecimal getDiscount();

}
