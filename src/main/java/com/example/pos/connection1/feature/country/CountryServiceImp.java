package com.example.pos.connection1.feature.country;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pos.connection1.entity.Country;
import com.example.pos.connection1.feature.country.dto.CountryRequest;
import com.example.pos.connection1.feature.country.dto.CountryResponse;
import com.example.pos.connection1.feature.country.dto.CountryUpdateRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryServiceImp implements CountryService {
     private final CountryRepository countryRepository;
     private String idNotFound = "Id has not been found .";

     @Override
     public void deleteById(int id) {
          Country country = countryRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
          countryRepository.delete(country);
     }

     @Override
     public CountryResponse updateById(int id, CountryUpdateRequest countryUpdateRequest) {
          Country country = countryRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

          country.setCountryName(countryUpdateRequest.countryName());
          country.setUuid(countryUpdateRequest.uuid());
          countryRepository.save(country);
          return  mapToCountryResponse(country);
     }

     @Override
     public CountryResponse readById(int id) {
          Country country = countryRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
          return mapToCountryResponse(country);
     }

     @Override
     public JavaCollectionResponse<?> read(int pageNumber, int pageSize) {

          // sort list by id desc
          Sort sortById = Sort.by(Sort.Direction.DESC, "id");
          // get pagination by 10 items per page
          PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

          Page<Country> pages = countryRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

          List<CountryResponse> content = pages.getContent().stream()
                    .map(this::mapToCountryResponse)
                    .toList();

          return JavaCollectionResponse.builder()
                    .content(content)
                    .count(pages.getTotalElements())
                    .build();
     }

     @Override
     public CountryResponse create(CountryRequest countryRequest) {

          Country country = Country.builder()
                    .countryName(countryRequest.countryName())
                    .uuid(countryRequest.uuid())
                    .createBy(countryRequest.createBy())
                    .isDeleted(false)
                    .status(true)
                    .build();
          countryRepository.save(country);

          return mapToCountryResponse(country);
     }

     private CountryResponse mapToCountryResponse(Country c) {
          return CountryResponse.builder()
                    .countryName(c.getCountryName())
                    .id(c.getId())
                    .uuid(c.getUuid())
                    .build();
     }

}
