package com.example.pos.system.feature.imports.dto;

import com.example.pos.system.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CheckingRequest(
          @NotNull(message = JavaMessage.required) Integer createBy,

          @NotBlank(message = JavaMessage.required) String remark,

          @NotBlank(message = JavaMessage.required) String role,

          @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "CheckDate format must be YYYY-MM-DD") 
          @NotBlank(message = JavaMessage.required)
          String checkDate) {

}
