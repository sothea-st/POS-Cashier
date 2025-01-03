package com.example.pos.system.feature.product.productExcel;

import com.example.pos.system.feature.product.productExcel.dto.ProductMultipleInsert;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.system.constant.util.response.JavaMessageResponse;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/productExcel")
@RequiredArgsConstructor
public class ProductExcelController {
     private final ProductExcelService productExcelService;

     @PostMapping
     public JavaMessageResponse<?> create(@Valid @RequestBody ProductMultipleInsert productMultipleInsert){

          productExcelService.create(productMultipleInsert);
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.success)
                    .data(JavaMessageResponse.success)
                    .build();
     }
} 
