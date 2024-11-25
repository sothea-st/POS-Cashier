package com.example.pos.system.layer.projections.discountProjection;

public interface DiscountProjection {
     Double getDiscount();
     Double getPrice();
     Integer getQty();
     Integer getQty_returned();
     String getDiscount_type();
}
