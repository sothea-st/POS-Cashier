package com.example.pos.system.feature.company_profile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BusinessRequest(
        @NotBlank(message = "The field customerName is required!")
        String customerName,

        @NotBlank(message = "The field companyName is required!")
        String companyName,

        @NotBlank(message = "The field phoneNumber is required!")
        @Size(min = 9,max = 12,message = "Phone number must be between 9 and 12 digits.")
        @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
        String phoneNumber,

        @Pattern(regexp = "^[a-zA-Z0-9._-]+@gmail\\.com$", message = "Email must be contain @gmail.com")
        @NotBlank(message = "The field email is required !")
        String email,
        String vatNumber,

        String home,
        String street,
        String province,
        String district,
        String commune,
        String village,

        @NotNull(message = "The field createdBy is required!")
        Integer createdBy

) {
}
