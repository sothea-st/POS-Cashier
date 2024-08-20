package com.example.pos.connection1.feature.tax;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.connection1.feature.tax.dto.TaxRequest;
import com.example.pos.connection1.feature.tax.dto.TaxRequestUpdate;
import com.example.pos.connection1.feature.tax.dto.TaxResponse;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/taxProduct")
@RequiredArgsConstructor

public class TaxController {
    private final TaxService taxService;

    // read tax by id
    @GetMapping("/{id}")
    TaxResponse readById(@PathVariable("id") Integer id){
        return  taxService.readById(id);
    }

    // read list tax
    @GetMapping
    JavaCollectionResponse<?> readList(
        @RequestParam(name = "pageSize", required = false) Integer pageSize, 
        @RequestParam(name = "pageNumber", required = false)Integer pageNumber){
        return  taxService.readList(pageSize, pageNumber);
    }

    //create tax
    @PostMapping
    TaxResponse create(@Valid @RequestBody TaxRequest taxRequest){
        return  taxService.create(taxRequest);
    }

    //update tax tax by id
    @PutMapping("/{id}")
    TaxResponse update(@PathVariable("id") Integer id, @Valid @RequestBody TaxRequestUpdate taxRequestUpdate){
        return taxService.update(id,taxRequestUpdate);
    }

    //delete tax by id
    @ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Integer id){
        taxService.deleteById(id);
    }

    //search tax by tax name
    @GetMapping("/searchTax/{taxName}")
    JavaCollectionResponse<?> search(
        @RequestParam(name = "pageSize", required = false) Integer pageSize, 
        @RequestParam(name = "pageNumber", required = false)Integer pageNumber,
        @PathVariable("taxName") String searchValue){
        return  taxService.search(pageSize, pageNumber, searchValue);
    }

}
