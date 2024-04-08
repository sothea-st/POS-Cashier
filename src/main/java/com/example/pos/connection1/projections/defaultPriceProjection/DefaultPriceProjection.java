package com.example.pos.connection1.projections.defaultPriceProjection;

import java.math.BigDecimal;

public interface DefaultPriceProjection {
    int getId();
    BigDecimal getDefault_price_usd();
    BigDecimal getdefault_price_khr();
}
