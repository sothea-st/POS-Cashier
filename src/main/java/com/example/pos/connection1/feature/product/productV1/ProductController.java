package com.example.pos.connection1.feature.product.productV1;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.connection1.feature.product.productV1.dto.ProductRequest;
import com.example.pos.connection1.feature.product.productV1.dto.ProductRequestVendorOrSubCateId;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import com.example.pos.connection1.util.response.JavaMessageResponse;
import jakarta.validation.Valid;

@RequestMapping("/api/product/v1")
@RestController
@RequiredArgsConstructor
public class ProductController {
     private final ProductService productService;

     @PostMapping
     public JavaMessageResponse<?> create(@Valid @RequestBody ProductRequest productRequest) {
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.success)
                    .data(productService.create(productRequest))
                    .build();
     }

     @GetMapping("/{id}")
     public JavaMessageResponse<?> readById(@PathVariable("id") int id) {
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.success)
                    .data(productService.readProductById(id))
                    .build();
     }

     @GetMapping
     public JavaCollectionResponse<?> read(
               @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
               @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {
          return productService.read(pageNumber, pageSize);
     }

     @GetMapping("/search/{value}")
     public JavaCollectionResponse<?> search(
               @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
               @RequestParam(name = "pageSize", defaultValue = "100", required = false) int pageSize,
               @PathVariable("value") String value) {
          return productService.search(pageNumber, pageSize, value);
     }

     @PostMapping("/vendor/subCategory")
     public JavaCollectionResponse<?> searchByVendorIdOrSubCategoryId(
               @Valid @RequestBody ProductRequestVendorOrSubCateId productRequestVendorOrSubCateId) {
          return productService.findByVendorIdOrSubCategoryId(productRequestVendorOrSubCateId);
     }

     @DeleteMapping("/{id}")
     public JavaMessageResponse<?> deleteById(@PathVariable("id") int id) {
          productService.deleteById(id);
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.success)
                    .data(JavaMessageResponse.success)
                    .build();
     }

     @PutMapping("/{id}")
     public JavaMessageResponse<?> updateById(@PathVariable("id") int id,
               @Valid @RequestBody ProductRequest productRequest) {
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.success)
                    .data(productService.updateProductById(id, productRequest))
                    .build();
     }

}
