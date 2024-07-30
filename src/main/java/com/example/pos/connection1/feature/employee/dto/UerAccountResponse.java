package com.example.pos.connection1.feature.employee.dto;

import lombok.Builder;

@Builder
public record UerAccountResponse(
    String userCode,
    String fullName,
    Integer id,
    Integer empId
) {

}
