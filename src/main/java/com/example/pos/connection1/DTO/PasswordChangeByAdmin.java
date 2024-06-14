package com.example.pos.connection1.DTO;

import com.example.pos.connection1.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;

public record PasswordChangeByAdmin(
     @NotBlank(message = JavaMessage.required)
     String newPassword,
     @NotBlank(message = JavaMessage.required)
     String confirmPassword,
     Integer empId
) {
     
}
