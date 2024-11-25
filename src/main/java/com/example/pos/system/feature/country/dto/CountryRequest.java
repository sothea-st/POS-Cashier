package com.example.pos.system.feature.country.dto;

import com.example.pos.system.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CountryRequest(
     @NotBlank(message = JavaMessage.required)
     String countryName,

     @NotNull(message = JavaMessage.required)
     Integer createBy,

     String uuid
) {
     
}
