package com.example.pos.connection1.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.DTO.PasswordRequest;
import com.example.pos.connection1.DTO.PasswordResponse;
import com.example.pos.connection1.authentication.services.ChangePasswordService;
import com.example.pos.connection1.components.JavaResponse;
import com.example.pos.connection1.constant.JavaValidation;

import java.util.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/changePassword")
@Slf4j
public class ChangePasswordController {
     @Autowired
     private ChangePasswordService changePasswordService;

     @PostMapping
     public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordRequest passwordRequest) {
          if (passwordRequest.userId() == null) {
               return ResponseEntity.ok().body(Map.of("userId", "The field userId is required!"));
          }

          if (!passwordRequest.newPassword().equals(passwordRequest.confirmPassword())) {
               return ResponseEntity.ok()
                         .body(Map.of("msg", "The field newPassword does not match with confirmPassword!"));
          }
          PasswordResponse msg = changePasswordService.changePassword(passwordRequest);
          if (!msg.msg().equals("success")) {
               return ResponseEntity.ok().body(Map.of("msg", msg.msg()));
          }
          HashMap<String, Object> error = new HashMap<>();
          String keyNewPassword = "newPassword";
          String newPassword = JavaValidation.checkPassword(passwordRequest.newPassword());

          String keyConfirmPassword = "confirmPassword";
          String confirmPassword = JavaValidation.checkPassword(passwordRequest.confirmPassword());
          if (!newPassword.isEmpty())
               error.put(keyNewPassword, newPassword);
          if (!confirmPassword.isEmpty())
               error.put(keyConfirmPassword, confirmPassword);
          if (!error.isEmpty())
               return ResponseEntity.status(500).body(error);

          changePasswordService.changePassword(passwordRequest);
          return ResponseEntity.ok().body(Map.of("msg", "success"));
     }

}
