package com.example.pos.system.feature.order_online.dto;

import lombok.Builder;

@Builder
public record OrderOnlineCalculate(
        CalculateCountAndSum newOrders,
        CalculateCountAndSum cancelled,
        CalculateCountAndSum completed
) {
}
