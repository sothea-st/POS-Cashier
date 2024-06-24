package com.example.pos.connection1.DTO.categoryDto;

import com.example.pos.connection1.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
     @NotBlank(message = JavaMessage.required)
     String catNameEn,

     String catNameKh,

     @NotNull(message = "Field createBy is required .!")
     Integer createBy,
   
     Integer parentId,

     String code
) {
     
}
