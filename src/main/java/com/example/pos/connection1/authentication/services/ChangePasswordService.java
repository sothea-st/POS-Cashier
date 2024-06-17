package com.example.pos.connection1.authentication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pos.connection1.DTO.PasswordChangeByAdmin;
import com.example.pos.connection1.DTO.PasswordRequest;
import com.example.pos.connection1.DTO.PasswordResponse;
import com.example.pos.connection1.entity.User;
import com.example.pos.connection1.repository.UserRepository;
import java.util.*;
@Service
public class ChangePasswordService {

     @Autowired
     private UserRepository userRepository;
     @Autowired
     private PasswordEncoder passwordEncoder;

     public PasswordResponse changePassword(PasswordRequest passwordRequest) {
          Optional<User> user = userRepository.findById(passwordRequest.userId());
          if( user.isEmpty() ) {
               return new PasswordResponse("userId have not been found !");
          }
       
          if (passwordEncoder.matches(passwordRequest.currentPassword(), user.get().getPassword())) {
          
               user.get().setPassword(passwordEncoder.encode(passwordRequest.newPassword()));
               userRepository.save(user.get());
          } else {
               return new PasswordResponse("current password does not match with old password");
          }

          return new PasswordResponse("success");
     }

     public PasswordResponse changePasswordByAdmin(PasswordChangeByAdmin p){
          Optional<User> user = userRepository.findByEmpId(p.empId());
          if( user.isEmpty() ) {
               return new PasswordResponse("userId have not been found !");
          }
          User userData = user.get();
          userData.setPassword(passwordEncoder.encode(p.newPassword()));
          userRepository.save(userData);
          return new PasswordResponse("success");
     }

}
