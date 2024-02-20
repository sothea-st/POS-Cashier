package com.example.pos.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.authentication.entity.User;
import com.example.pos.authentication.repositories.UserRepository;
import com.example.pos.components.JavaResponse;

import java.util.*;
@RestController
@RequestMapping("/api/logout")
public class LogoutController {
     @Autowired
     private UserRepository repo;

     @PostMapping("/{id}")
     public ResponseEntity<?> logout(@PathVariable("id") int id) {
          Optional<User> user = repo.findById(id);
          User data = user.get();
          data.setDevice(null);
          repo.save(data);
          return JavaResponse.success("Log out success");
     }

}
