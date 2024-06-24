package com.example.pos.connection1.projections.ReportImport;

import java.math.BigDecimal;

public interface ReportSaledProjection {
     // psd.qty,
	// pp.cost,
	// psd.price,
	// psd.amount,
	// psd.discount as discount_percentage,
	// pp.pro_name_en,
	// pp.pro_image_name,
	// ps.sale_date,
	// ppt.tax_name,
	// ps.discount_case,
	// ps.discount

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
}
