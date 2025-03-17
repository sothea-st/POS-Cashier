package com.example.pos.system.layer.projections.ReportImport;

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
     String getinvoice_number();
     String getChoices();

     String getpos_id();
     String getpayment_method();

     BigDecimal gettotal_order();

     String getorder_source();
     String getcustomer_type();
}
