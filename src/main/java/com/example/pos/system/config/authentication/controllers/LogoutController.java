package com.example.pos.system.config.authentication.controllers;

import com.example.pos.system.config.authentication.dtos.UserLogoutDTO;
import com.example.pos.system.constant.JavaResponse;
import com.example.pos.system.layer.repository.IPAddressRepository;
import com.example.pos.system.layer.repository.SaleRepository;
import com.example.pos.system.layer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.system.domain.User;

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


     @Autowired
     private SaleRepository saleRepository;
 

     @PostMapping
     public ResponseEntity<?> logout(@RequestBody UserLogoutDTO user) {
        
          Optional<User> users = repo.findById(user.id());
          jdbcTemplate.update("delete from pos_id where user_id = "+user.id()+"");


          String updateQuery = "update pos_sale set active = ? where pos_id = ? and user_code = ? and user_id = ?";
        
          // Define values for parameters in the update query
          Object[] params = {null, user.posId(), user.userCode() , user.id()}; // Example values
          
          // Execute the update query
          jdbcTemplate.update(updateQuery, params);

          String updateCloseSift = "update pos_close_shift set active = ? where pos_id = ? and user_code = ?";
          Object[] obj = {null, user.posId(), user.userCode() }; // Example values
          jdbcTemplate.update(updateCloseSift, obj);

 

          User data = users.get();
          data.setDevice(null);
          repo.save(data);
          return JavaResponse.success("Log out success");
     }

}
