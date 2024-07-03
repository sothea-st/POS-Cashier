package com.example.pos.connection1.feature.country.dto;

import lombok.Builder;

@Builder
public record CountryResponse(
     int id,
     String countryName,
     String uuid
) {
     
}
