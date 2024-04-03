package com.example.pos.connection1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.components.JavaResponse;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "http://localhost:3000") 
public class TestAPIController {
    @GetMapping("/hello")
    public ResponseEntity<?> test(){
        return JavaResponse.success("hello");
    }
}
