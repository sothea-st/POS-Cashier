package com.example.pos.system.feature.company_profile.dto;

import com.example.pos.system.constant.JavaMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record IndividualRequest(
        @NotBlank(message = "The field firstName is required!")
        String firstName,

        @NotBlank(message = "The field lastName is required!")
        String lastName,

        @NotBlank(message = "The field gender is required!")
        String gender,

        String nationality,

        @NotBlank(message = "The field phoneNumber is required!")
        @Size(min = 9,max = 12,message = "Phone number must be between 9 and 12 digits.")
        @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
        String phoneNumber,

        @Pattern(regexp = "^[a-zA-Z0-9._-]+@gmail\\.com$", message = "Email must be contain @gmail.com")
        @NotBlank(message = "The field email is required !")
        String email,

        @NotBlank(message = "The field dob is required!")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth must be in the format yyyy-MM-dd!")
        String dob,

        String home,
        String lat,
        String lng,
        String street,
        String province,
        String district,
        String commune,
        String village,
        String profileName,

        @NotNull(message = "The field createdBy is required!")
        Integer createdBy

) {

}
