package com.example.pos.connection1.feature.imports;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.constant.JavaConstant;
import com.example.pos.connection1.feature.imports.dto.CheckingRequest;
import com.example.pos.connection1.feature.imports.dto.ImportRequest;
import com.example.pos.connection1.feature.imports.dto.RejectPurchaseOrderRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import com.example.pos.connection1.util.response.JavaMessageResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/import")
@RestController
@RequiredArgsConstructor
public class ImportController {
     private final ImportService importService;

     @PostMapping("/rejectPurchaseOrder/{id}")
     public JavaMessageResponse<?> rejectPurchaseOrder(@Valid @RequestBody RejectPurchaseOrderRequest data,
               @PathVariable("id") int id) {
          importService.rejectPurchaseOrder(data, id);
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.success)
                    .data(JavaMessageResponse.success)
                    .build();
     }

     @PostMapping("/checkingRequest/{poId}")
     public JavaMessageResponse<?> checkingRequest(@Valid @RequestBody CheckingRequest checkingRequest,
               @PathVariable("poId") String poId) {
          importService.checkingRequest(checkingRequest, poId);
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.success)
                    .data(JavaMessageResponse.success)
                    .build();
     }

     @PostMapping
     public JavaMessageResponse<?> createImport(@Valid @RequestBody ImportRequest importRequest) {
          importService.createImport(importRequest);
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.success)
                    .data(JavaMessageResponse.success)
                    .build();
     }

     @GetMapping("/getPoId")
     public JavaCollectionResponse<?> purchaseOrderResponse() {
          return importService.purchaseOrderResponse();
     }

     @PutMapping("/{id}")
     public JavaMessageResponse<?> update(@PathVariable("id") int id, @Valid @RequestBody ImportRequest importRequest) {
          importService.update(importRequest, id);
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.success)
                    .data(JavaMessageResponse.success)
                    .build();
     }

     @GetMapping
     public JavaCollectionResponse<?> retrieve(
               @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
               @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {
          return importService.retrieve(pageNumber, pageSize);
     }

     @GetMapping("/filter/{value}")
     public JavaCollectionResponse<?> filter(
               @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
               @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize,
               @PathVariable("value") String value) {
          return importService.filter(pageNumber, pageSize, value);
     }

     @DeleteMapping("/{id}")
     public JavaMessageResponse<?> deleteById(@PathVariable("id") int id) {
          importService.deleteById(id);
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.deleteSuccess)
                    .data(JavaMessageResponse.deleteSuccess)
                    .build();
     }

     @GetMapping("/{id}")
     public JavaMessageResponse<?> retrieveDetailById(@PathVariable("id") int id) {
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.success)
                    .data(importService.retrieveDetail(id))
                    .build();
     }

}
