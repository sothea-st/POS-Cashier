package com.example.pos.system.feature.reports.report_stock.stock.dto;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record StockResponse(
        String productName,
        String productImage,
        String categoryName,
        String supplierName,
        BigDecimal price,
        BigDecimal cost,
        Integer qty,
        String date,
        String description
) {
}
