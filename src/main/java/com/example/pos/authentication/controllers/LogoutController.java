package com.example.pos.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.User;
import com.example.pos.repository.UserRepository;

import java.util.*;
@RestController
@RequestMapping("/api/logout")
public class LogoutController {
     @Autowired
     private UserRepository repo;

     @PostMapping
     public ResponseEntity<?> logout(@RequestBody User user) {
          Optional<User> users = repo.findById(user.getId());
          User data = users.get();
          data.setDevice(null);
          repo.save(data);
          return JavaResponse.success("Log out success");
     }

}
