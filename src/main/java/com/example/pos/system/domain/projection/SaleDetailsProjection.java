package com.example.pos.system.domain.projection;
import java.math.*;
public interface SaleDetailsProjection {
    BigDecimal getAmount();
    int getQty();
}
