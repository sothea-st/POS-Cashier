package com.example.pos.connection1.feature.attribute;

import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.feature.attribute.dto.AttributeRequest;
import com.example.pos.connection1.feature.attribute.dto.AttributeResponse;
import com.example.pos.connection1.feature.attribute.dto.AttributeUpdateRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/attribute")
@RequiredArgsConstructor

public class AttributeController {
    private final AttributeService attributeService;
    
    /*
      * get all attribute
    */
    @GetMapping
    JavaCollectionResponse<?> read (
        @RequestParam(defaultValue = "10", required = false ) int pageSize,
        @RequestParam(defaultValue = "0", required = false) int pageNumber
    ){
        return attributeService.read(pageSize, pageNumber);
    }

    /*
      * get attribute by id
    */
    @GetMapping("/{id}")
    AttributeResponse readById (@PathVariable("id") Integer id){
        return attributeService.readById(id);
    }

    /*
      * create attribute
    */
    @PostMapping
    AttributeResponse create(@Valid @RequestBody AttributeRequest attributeRequest){
        return attributeService.create(attributeRequest);
    }
    
    /*
      * update attribute
    */
    @PutMapping("/{id}")
    AttributeResponse updateById(@PathVariable("id") Integer id, @Valid @RequestBody AttributeUpdateRequest attributeUpdateRequest){
        return attributeService.updateById(id, attributeUpdateRequest);
    }

    /*
      * delete attribute
    */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Integer id){
        attributeService.deleteById(id);
    }

}
