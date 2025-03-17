package com.example.pos.system.feature.company_profile.dto;

import lombok.Builder;

@Builder
public record IndividualResponse(
    Integer id,
    String customerId,
    String firstName,
    String lastName,
    String gender,
    String nationality,
    String phoneNumber,
    String email,
    String dob,
    String profileImage,
    String home,
    String lat,
    String lng,
    String street,
    String province,
    String district,
    String commune,
    String village,
    String fullAddressKh,
    String fullAddressEn,
        String createdDate
) {
}
