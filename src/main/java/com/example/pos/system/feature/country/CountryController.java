package com.example.pos.system.feature.country;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.system.feature.country.dto.CountryRequest;
import com.example.pos.system.feature.country.dto.CountryResponse;
import com.example.pos.system.feature.country.dto.CountryUpdateRequest;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
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

    //Update Country by id
    @PutMapping("/{id}")
    public CountryResponse upateById(@PathVariable("id") Integer id,@RequestBody CountryUpdateRequest countryUpdateRequest) {
        return countryService.updateById(id, countryUpdateRequest);
    }

    //read country by id
    @GetMapping("/{id}")
    public CountryResponse readByUuid(@PathVariable("id") Integer id) {
        return countryService.readById(id);
    }

    //Create country
    @PostMapping
    public CountryResponse create(@Valid @RequestBody CountryRequest countryRequest) {
        return countryService.create(countryRequest);
    }

    //get list country
    @GetMapping
    public JavaCollectionResponse<?> read(
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber) {
        return countryService.read(pageSize, pageNumber);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	void deleteById(@PathVariable("id") Integer id) {
		countryService.deleteById(id);
	}

    //search country by country name
    @GetMapping("/searchCountry/{countryName}")
    public JavaCollectionResponse<?> search (
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @PathVariable("countryName") String searchValue) {
        return countryService.search(pageSize,pageNumber,searchValue);
    }

}
