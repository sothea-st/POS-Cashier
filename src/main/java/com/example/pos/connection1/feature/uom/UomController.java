package com.example.pos.connection1.feature.uom;

import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.feature.uom.dto.UomRequest;
import com.example.pos.connection1.feature.uom.dto.UomResponse;
import com.example.pos.connection1.feature.uom.dto.UomUpdateRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/uom")
@RequiredArgsConstructor

public class UomController {
    private final UomService uomService;

    @GetMapping
    JavaCollectionResponse<?> read (
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(defaultValue = "0", required = false) int pageNumber ){
        
        return uomService.read(pageSize, pageNumber);
    }
    
    @PostMapping 
    UomResponse create(@Valid @RequestBody UomRequest uomRequest){
        return uomService.create(uomRequest);
    }

    @GetMapping("/{id}")
    UomResponse readById(@PathVariable("id") Integer id){
        return uomService.readById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Integer id){
        uomService.deleteById(id);
    }

    @PutMapping("/{id}")
    UomResponse updateById(@PathVariable("id") Integer id, @Valid @RequestBody UomUpdateRequest uomUpdateRequest){
        return uomService.updateById(id, uomUpdateRequest);
    }
}
