package com.example.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.Hold;
import com.example.pos.entity.HoldeDetails;
import com.example.pos.entity.models.HoldModel;
import com.example.pos.projections.holdProjection.HoldDataProjection;
import com.example.pos.projections.holdProjection.HoldDetailsProjection;
import com.example.pos.projections.holdProjection.HoldProjection;
import com.example.pos.repository.HoldRepository;
import com.example.pos.service.HoldService;
import java.util.*;

@RestController
@RequestMapping("/api/hold")
public class HoldController {
     @Autowired
     private HoldService service;

     @Autowired
     private HoldRepository repo;

     @PostMapping
     public ResponseEntity<?> addHolde(@RequestBody Hold h) {
          Hold data = service.addHold(h);
          return JavaResponse.success(data);
     }

     @GetMapping
     public ResponseEntity<?> getHold() {
          HashMap<String ,Object> data = service.getHold();
          long count = repo.count();
          return ResponseEntity.ok().body(Map.of("msg","success","data",data,"count",count));
     }

     @DeleteMapping
     public ResponseEntity<?> deleteHold(@RequestBody Hold h) {
          service.deleteHold(h);
          return JavaResponse.success("delete success");
     }

     // @GetMapping("/{id}")
     // public ResponseEntity<?> getHoldById(@PathVariable("id") int id) {
     //      HashMap<String, Object> data = service.getHoldById(id);
     //      return JavaResponse.success(data);
     // }

}
