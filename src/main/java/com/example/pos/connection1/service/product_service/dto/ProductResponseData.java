package com.example.pos.connection1.service.product_service.dto;

public interface ProductResponseData {
    int getId();
    int getCat_id();
    int getBrand_id();
    String getFlag();
    String getWeight();
    String getPro_image_name();
    String getBarcode();
    String getPro_name_kh();
    String getPro_name_en();
    Double getCost();
    Double getPrice();
    String getProduct_status();
    Double getDiscount();
    String getCode_out_stock();
    String getCode_expired();

}
