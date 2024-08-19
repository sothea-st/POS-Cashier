package com.example.pos.connection1.feature.country.dto;

import com.example.pos.connection1.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;

public record CountryUpdateRequest(
     @NotBlank(message = JavaMessage.required)
     String countryName,
     
     String uuid
) {
     
}
