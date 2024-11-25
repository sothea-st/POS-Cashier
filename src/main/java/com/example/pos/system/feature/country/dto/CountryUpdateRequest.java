package com.example.pos.system.feature.country.dto;

import com.example.pos.system.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;

public record CountryUpdateRequest(
     @NotBlank(message = JavaMessage.required)
     String countryName,
     
     String uuid
) {
     
}
