package com.example.pos.connection1.projections.discountProjection;

public interface DiscountProjection {
     Double getDiscount();
     Double getPrice();
     Integer getQty();
     Integer getQty_returned();
     String getDiscount_type();
}
