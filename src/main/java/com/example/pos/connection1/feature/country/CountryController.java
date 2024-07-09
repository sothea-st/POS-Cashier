package com.example.pos.connection1.feature.country;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.connection1.feature.country.dto.CountryRequest;
import com.example.pos.connection1.feature.country.dto.CountryResponse;
import com.example.pos.connection1.feature.country.dto.CountryUpdateRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PutMapping("/{id}")
    public CountryResponse upateById(@PathVariable("id") int id,@RequestBody CountryUpdateRequest countryUpdateRequest) {
        System.out.println("country name : " + countryUpdateRequest.countryName());
        System.out.println("country uuid : " + countryUpdateRequest.uuid());
        return countryService.updateById(id, countryUpdateRequest);
    }

    @GetMapping("/{id}")
    public CountryResponse readByUuid(@PathVariable("id") int id) {
        return countryService.readById(id);
    }

    @PostMapping
    public CountryResponse create(@Valid @RequestBody CountryRequest countryRequest) {
        return countryService.create(countryRequest);
    }

    @GetMapping
    public JavaCollectionResponse<?> read(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return countryService.read(pageNumber, pageSize);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        countryService.deleteById(id);
    }

    @GetMapping("/searchCountry/{countryName}")
    public JavaCollectionResponse<?> search (
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize,
            @PathVariable("countryName") String searchValue) {
        return countryService.search(pageNumber, pageSize, searchValue);
    }

}
