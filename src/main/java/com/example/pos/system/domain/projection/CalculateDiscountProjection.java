package com.example.pos.system.domain.projection;
import java.math.BigDecimal;
public interface CalculateDiscountProjection {
    int getQty();
    BigDecimal getPrice();
}
