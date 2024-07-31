package com.example.pos.connection1.feature.category;


import com.example.pos.connection1.DTO.categoryDto.CategoryResponse;
import com.example.pos.connection1.components.JavaResponse;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;


    @GetMapping("/code/{code}")
    public JavaCollectionResponse<?> getCategoryByCode(
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber ,
            @RequestParam(name = "pageSize" , defaultValue = "10") int pageSize ,
            @Valid @PathVariable("code") String code
    ) {
        return  service.read(pageNumber,pageSize,code);
    }


    @GetMapping("/code/{code}/search/{catNameEn}")
    public JavaCollectionResponse<?> search(
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber ,
            @RequestParam(name = "pageSize" , defaultValue = "10") int pageSize ,
            @Valid @PathVariable("code") String code,
            @PathVariable("catNameEn") String searchValue
    ) {
        return  service.search(pageNumber,pageSize,code,searchValue);
    }
}
