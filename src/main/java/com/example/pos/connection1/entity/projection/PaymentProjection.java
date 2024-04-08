package com.example.pos.connection1.entity.projection;

import java.math.BigDecimal;

public interface PaymentProjection {
    BigDecimal getTotal();
    BigDecimal getReceive_usd();
    String getReceive_khr();
    BigDecimal getChange_usd();
    String getChange_khr();
    BigDecimal getRemaining_usd();
    String getRemaining_khr();
    String getPayment_no();
    String getPayment_barcode();
    String getSale_date();
    String getCustomer_type();
    int getSale_id();
    String getFull_name();
    int getUser_id();
    String getIs_return();
    BigDecimal getDiscount();
}
