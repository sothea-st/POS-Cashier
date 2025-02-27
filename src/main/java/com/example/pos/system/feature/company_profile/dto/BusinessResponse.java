package com.example.pos.system.feature.company_profile.dto;

import lombok.Builder;

@Builder
public record BusinessResponse(
        String customerName,
        String companyName,
        String phoneNumber,
        String email,
        String vatNumber,
        String home,
        String street,
        String province,
        String district,
        String commune,
        String village,
        String fullAddressKh,
        String fullAddressEn
) {
}
