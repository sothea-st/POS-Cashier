package com.example.pos.projections.holdProjection;
import java.math.*;
public interface HoldProjection {
     int getId();
     int getCat_id();
     int getBrand_id();
     String getFlag();
     String getWeight();
     String getPro_image_name();
     String getBarcode();
     String getPro_name_kh();
     String getPro_name_en();
     double getCost();
     double getPrice();
     String getProduct_status();
     String getCode_out_stock();
     String getCode_expired();
     int getQty();
     BigDecimal getDiscount();
     String getDiscount_type();
     
}  
