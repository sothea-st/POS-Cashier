package com.example.pos.system.feature.attribute.product.productV1.dto;

import java.math.BigDecimal;

public interface ProductResponseReadByProductId {
    Integer getId(); // productId
    BigDecimal getCost();
    BigDecimal getPrice();
    Integer getQty_old();
    String getLocal_date();
}
