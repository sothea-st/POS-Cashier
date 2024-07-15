package com.example.pos.connection1.feature.imports;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.feature.imports.dto.ImportRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import com.example.pos.connection1.util.response.JavaMessageResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.mapstruct.ap.shaded.freemarker.core.ReturnInstruction.Return;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/import")
@RestController
@RequiredArgsConstructor
public class ImportController {
     private final ImportService importService;

     @PostMapping
     public JavaMessageResponse<?> createImport(@Valid @RequestBody ImportRequest importRequest) {
          importService.createImport(importRequest);
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.success)
                    .data(JavaMessageResponse.success)
                    .build();
     }

     @GetMapping
     public JavaCollectionResponse<?> retrieve(
               @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
               @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
     ) {
          return importService.retrieve(pageNumber, pageSize);
     }

     @DeleteMapping("/{id}")
     public JavaMessageResponse<?> deleteById(@PathVariable("id") int id){
          importService.deleteById(id);
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.deleteSuccess) 
                    .data(JavaMessageResponse.deleteSuccess)
                    .build();
     }

     @GetMapping("/{id}")
     public JavaMessageResponse<?> retrieveDetailById(@PathVariable("id") int id){
          return JavaMessageResponse.builder()
                    .status(HttpStatus.OK.value())
                    .msg(JavaMessageResponse.deleteSuccess) 
                    .data(importService.retrieveDetail(id))
                    .build();
     }

}
