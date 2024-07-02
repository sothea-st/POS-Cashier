package com.example.pos.connection1.feature.vendor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;

@Builder
 
public record VendorResponse(
     int id,
     String vendorName,
     String address,
     String contact,
     String email,
     String website,
     String uuid,
     String vdCode
) {
     
}
