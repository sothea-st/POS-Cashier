package com.example.pos.connection1.feature.employee.dto;

import java.util.Date;

import lombok.Builder;

@Builder
public record EmployeeResponse(
    Integer id,
    String nameKh,
    String nameEn,
    String gender,
    String dob,
    String startDate,
    String imageName,
    String contact,
    Integer roleId,
    String address,
    Integer createBy,
    Date createDate,
    Boolean status,
    Boolean deleted
) {

}
