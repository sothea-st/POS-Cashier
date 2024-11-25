package com.example.pos.system.config.authentication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pos.system.layer.DTO.PasswordChangeByAdmin;
import com.example.pos.system.layer.DTO.PasswordRequest;
import com.example.pos.system.layer.DTO.PasswordResponse;
import com.example.pos.system.domain.User;
import com.example.pos.system.layer.repository.UserRepository;

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
