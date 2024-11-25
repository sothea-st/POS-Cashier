package com.example.pos.system.layer.DTO;

import com.example.pos.system.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;

public record PasswordChangeByAdmin(
     @NotBlank(message = JavaMessage.required)
     String newPassword,
     @NotBlank(message = JavaMessage.required)
     String confirmPassword,
     Integer empId
) {
     
}
