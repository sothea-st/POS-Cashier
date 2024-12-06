package com.example.pos.system.feature.user_permission.reports.report_inventoory.dto;

import lombok.Builder;

@Builder
public record ReportInventoryRequest(
        Integer productId,
        String impDate,
        Integer stockInQty,
        Integer stockOutQty,
        Integer returnInQty,
        Integer returnOutQty
) {
}
