package com.example.pos.system.feature.user_permission.reports.report_inventoory.dto;

import lombok.Builder;

@Builder
public record ReportInventoryResponse(
        Long id,
        String date,
        String productName,
        Integer beginningQty,
        Integer stockInQty,
        Integer availableQty,
        Integer returnOutQty,
        Integer returnInQty,
        Integer stockOutQty,
        Integer endingQty

) {
}
