// package com.example.pos.connection2.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.pos.connection1.components.JavaResponse;
// import com.example.pos.connection2.repository.ProductCategoryRepo;
// import com.example.pos.connection2.service.PurchaseOrderDetailService;

// @RestController
// @RequestMapping("/api/getProductByCategory")
// public class ProductByCategoryController {

//      @Autowired
//      private ProductCategoryRepo repo;

//      @Autowired 
//      private PurchaseOrderDetailService service;

//      @GetMapping
//      public ResponseEntity<?> getData(){
//           return ResponseEntity.ok().body(repo.getData());
//      }
//      @GetMapping("/category")
//      public ResponseEntity<?> getCategory(){
//           return JavaResponse.success(service.getCategory());
//      }

// }
