package com.example.pos.system.feature.order_online.dto;

import java.math.BigDecimal;

public record OrderOnlineDetailRequest(
    Integer productId,
    Integer qtySale,
    BigDecimal discountPrice

) {
}
