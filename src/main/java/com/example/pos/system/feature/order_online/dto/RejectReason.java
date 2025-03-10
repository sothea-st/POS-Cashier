package com.example.pos.system.feature.order_online.dto;

import jakarta.validation.constraints.NotEmpty;

public record RejectReason(
        @NotEmpty(message = "The field reason is required!")
        String reason
) {
}
