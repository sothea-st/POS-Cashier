package com.example.pos.system.feature.employee.dto;

import lombok.Builder;

@Builder
public record UerAccountResponse(
    String userCode,
    String fullName,
    Integer id,
    Integer empId
) {

}
