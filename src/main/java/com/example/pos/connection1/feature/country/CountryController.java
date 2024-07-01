package com.example.pos.connection1.feature.country;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pos.connection1.feature.country.dto.CountryRequest;
import com.example.pos.connection1.feature.country.dto.CountryResponse;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

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

}
