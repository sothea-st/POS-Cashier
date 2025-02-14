package com.example.pos.system.layer.projections.exchange_projection;

import java.math.BigDecimal;

public interface ExchangeProjection {
    BigDecimal getChange_khr();

    BigDecimal getChange_usd();
}
