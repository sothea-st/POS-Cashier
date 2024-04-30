package com.example.pos.connection2.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.components.JavaResponse;
import com.example.pos.connection2.service.ServiceManagement;

@RestController
@RequestMapping("/api/d2")
public class RouteControllerD2 {
     @Autowired
     private ServiceManagement serviceManagement;

     @GetMapping("/product")
     public ResponseEntity<?> getProduct(){
          return JavaResponse.success(serviceManagement.getProduct());
     }

}
