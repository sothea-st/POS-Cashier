package com.example.pos.connection1.feature.country.dto;

import lombok.Builder;

@Builder
public record CountryResponse(
     String uuid,
     String countryName
) {
     
}
