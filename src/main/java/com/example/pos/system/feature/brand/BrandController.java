package com.example.pos.system.feature.brand;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.pos.system.feature.brand.dto.BrandRequest;
import com.example.pos.system.feature.brand.dto.BrandRequestUpdate;
import com.example.pos.system.feature.brand.dto.BrandResponse;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandServices brandServices;

    @GetMapping("/{id}")
    BrandResponse readById(@PathVariable("id")  Integer id){
        return brandServices.readById(id);
    }

    @GetMapping
    JavaCollectionResponse<?> read(
        @RequestParam(name = "pageSize", required = false) Integer pageSize, 
        @RequestParam(name = "pageNumber", required = false) Integer pageNumber){
        return brandServices.read(pageSize, pageNumber);
    }

    @GetMapping("/searchBrand/{brandNameEn}")
    JavaCollectionResponse<?> search(
        @RequestParam(name = "pageSize", required = false) Integer pageSize, 
        @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
        @PathVariable("brandNameEn") String searchValue){
        return brandServices.search(pageSize, pageNumber,searchValue);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	void deleteById(@PathVariable("id") Integer id) {
		brandServices.deleteById(id);
	}

    @PostMapping
    BrandResponse create(@Valid @RequestBody BrandRequest brandRequest){
        return brandServices.create(brandRequest);
    }
    
    @PutMapping("/{id}")
    BrandResponse update(@PathVariable("id") Integer id, @Valid @RequestBody BrandRequestUpdate brandRequestUpdate){
        return brandServices.update(id, brandRequestUpdate);
    }
        
}
