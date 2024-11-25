package com.example.pos.system.feature.vendor.dto;

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
