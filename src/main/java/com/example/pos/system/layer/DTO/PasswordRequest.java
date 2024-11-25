package com.example.pos.system.layer.DTO;

import com.example.pos.system.constant.JavaMessage;

import jakarta.validation.constraints.NotBlank;

public record PasswordRequest(
     @NotBlank(message = JavaMessage.required)
     String currentPassword,
     @NotBlank(message = JavaMessage.required)
     String newPassword,
     @NotBlank(message = JavaMessage.required)
     String confirmPassword,
      
     Integer userId
) {
     
}
