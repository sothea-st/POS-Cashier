package com.example.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.components.JavaResponse;
import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.Hold;
import com.example.pos.repository.HoldRepository;
import com.example.pos.service.HoldService;
import java.util.*;

// @RestController
// @RequestMapping("/api/hold")
public class HoldController {
     // @Autowired
     // private HoldService service;

     // @Autowired
     // private HoldRepository repo;

     // @PostMapping
     // public ResponseEntity<?> addHolde(@RequestBody Hold h) {
     //      Hold data = service.addHold(h);
     //      return JavaResponse.success(data);
     // }

     // @GetMapping
     // public ResponseEntity<?> getHold() {
     //      HashMap<String ,Object> data = service.getHold();
     //      long count = repo.count();
     //      data.put("count", count);
     //      data.put("msg", "success");
     //      return ResponseEntity.ok().body(data);
     // }

     // @DeleteMapping
     // public ResponseEntity<?> deleteHold(@RequestBody Hold h) {
     //      service.deleteHold(h);
     //      return JavaResponse.success("delete success");
     // }

     // @GetMapping("/deleteByItem")
     // public ResponseEntity<?> deleteByItem(@RequestParam("holdId") int holdId , @RequestParam("proId") int proId){
     //     service.deleteHoldByItem(holdId, proId);
     //      return JavaResponse.success(JavaConstant.success);
     // }

     // @GetMapping("/{id}")
     // public ResponseEntity<?> getHoldById(@PathVariable("id") int id) {
     //      HashMap<String, Object> data = service.getHoldById(id);
     //      return JavaResponse.success(data);
     // }

}
