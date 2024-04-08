package com.example.pos.connection1.entity.projection;
import java.math.*;
public interface SaleDetailsProjection {
    BigDecimal getAmount();
    int getQty();
}
