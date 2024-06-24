package com.example.pos.connection1.DTO;

import jakarta.validation.constraints.NotBlank;

public record ReportRequest(
     @NotBlank(message = "The field dateFrom is required!")
     String dateFrom,

     @NotBlank(message = "The field dateTo is required!")
     String dateTo
) {
     
}
