package com.example.pos.connection1.feature.country.dto;

import com.example.pos.connection1.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CountryRequest(
     @NotBlank(message = JavaMessage.required)
     String countryName,

     @NotNull(message = JavaMessage.required)
     Integer createBy,

     @NotBlank(message = JavaMessage.required)
     String uuid
) {
     
}
