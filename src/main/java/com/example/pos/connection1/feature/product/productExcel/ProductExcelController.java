package com.example.pos.connection1.feature.product.productExcel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.connection1.feature.product.productExcel.dto.ProductMultipleInsert;
import com.example.pos.connection1.util.response.JavaMessageResponse;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/productExcel")
@RequiredArgsConstructor
public class ProductExcelController {
     private final ProductExcelService productExcelService;

     @PostMapping
     public JavaMessageResponse<?> create(@RequestBody ProductMultipleInsert productMultipleInsert){ 
          productExcelService.create(productMultipleInsert);
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.success)
                    .data(JavaMessageResponse.success)
                    .build();
     }
} 
