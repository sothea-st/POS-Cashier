package com.example.pos.connection1.feature.employee.dto;

import java.util.Date;

public record EmployeeResponse(
    String id,
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
