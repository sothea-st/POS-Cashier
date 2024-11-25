package com.example.pos.system.feature.country.dto;

import lombok.Builder;

@Builder
public record CountryResponse(
     int id,
     String countryName,
     String uuid
) {
     
}
