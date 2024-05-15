package com.example.pos.connection1.authentication.controllers;

import com.example.pos.connection1.components.JavaResponse;
import com.example.pos.connection1.repository.IPAddressRepository;
import com.example.pos.connection1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.entity.User;

import java.util.*;
@RestController
@RequestMapping("/api/logout")
public class LogoutController {
     @Autowired
     private UserRepository repo;

     @Autowired
     private IPAddressRepository ipAddressRepository;

     @Autowired
     private JdbcTemplate jdbcTemplate;

     @PostMapping
     public ResponseEntity<?> logout(@RequestBody User user) {
          Optional<User> users = repo.findById(user.getId());

          jdbcTemplate.update("delete from pos_id where user_id = "+user.getId()+"");
          
          User data = users.get();
         
          data.setDevice(null);
          repo.save(data);
          return JavaResponse.success("Log out success");
     }

}
