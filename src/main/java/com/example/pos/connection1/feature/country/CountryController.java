package com.example.pos.connection1.feature.country;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.connection1.feature.country.dto.CountryRequest;
import com.example.pos.connection1.feature.country.dto.CountryResponse;
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

@RestController
@RequestMapping("/api/country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/{uuid}")
    public CountryResponse readByUuid(@PathVariable("uuid") String uuid){
        return countryService.readByUuid(uuid);
    }

    @PostMapping
    public CountryResponse create(@Valid @RequestBody CountryRequest countryRequest) {
        return countryService.create(countryRequest);
    }

    @GetMapping
    public JavaCollectionResponse<?> read(
        @RequestParam(name = "pageNumber" , defaultValue = "0" , required = false) int pageNumber ,
        @RequestParam(name = "pageSize" , defaultValue = "10",required = false) int pageSize
    ){
        return countryService.read(pageNumber, pageSize);
    }

    @DeleteMapping("/{uuid}")
    public void deleteByUuid(@PathVariable("uuid") String uuid){
        countryService.deleteByUuid(uuid);
    }

    @PutMapping("/{uuid}")
    public CountryResponse upateByUuid(@PathVariable("uuid") String uuid , @Valid CountryRequest countryRequest) {
        System.out.println("ddsfsdfdf");
        return countryService.updateByUuid(uuid, countryRequest);
    }

}
