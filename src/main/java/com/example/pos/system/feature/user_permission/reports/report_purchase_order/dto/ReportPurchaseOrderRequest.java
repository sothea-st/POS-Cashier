package com.example.pos.system.feature.user_permission.reports.report_purchase_order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ReportPurchaseOrderRequest(
     @NotBlank(message = "The field dateFrom is required!")
     @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "dateFrom format must be YYYY-MM-DD") 
     String dateFrom,

     @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "dateTo format must be YYYY-MM-DD") 
     @NotBlank(message = "The field dateTo is required!") String dateTo
) {
     
}
