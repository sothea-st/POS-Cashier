package com.example.pos.connection2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection2.service.PurchaseOrderDetailService;

@RestController
@RequestMapping("/api/updatePurchaseOrder")
public class PurchaseOrderDetailController {

     @Autowired
     private PurchaseOrderDetailService service;
     
     @PutMapping
     public ResponseEntity<?> update(@RequestParam("productId") int productId){
          service.updateOrderQty(productId);
          return ResponseEntity.ok().body("success");
     }

}
