package com.example.pos.system.feature.order_online.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CalculateCountAndSum(
        long count,
        BigDecimal total
) {
}
