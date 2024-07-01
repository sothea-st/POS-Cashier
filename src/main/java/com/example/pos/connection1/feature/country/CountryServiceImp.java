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
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryServiceImp implements CountryService {
     private final CountryRepository countryRepository;
     private String uuidNotFound = "Uuid has not been found .";

     @Override
     public void deleteByUuid(String uuid) {
          Country country = countryRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, uuidNotFound));
          countryRepository.delete(country);
     }

     @Override
     public CountryResponse updateByUuid(String uuid, CountryRequest countryRequest) {
          Country country = countryRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, uuidNotFound));

          country.setCountryName(countryRequest.countryName());
          country.setUuid(countryRequest.uuid());
          countryRepository.save(country);
          return CountryResponse
                    .builder()
                    .countryName(country.getCountryName())
                    .uuid(country.getUuid())
                    .build();
     }

     @Override
     public CountryResponse readByUuid(String uuid) {
          Country country = countryRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, uuidNotFound));
          return CountryResponse
                    .builder()
                    .countryName(country.getCountryName())
                    .uuid(country.getUuid())
                    .build();
     }

     @Override
     public JavaCollectionResponse<?> read(int pageNumber, int pageSize) {

          // sort list by id desc
          Sort sortById = Sort.by(Sort.Direction.DESC, "id");
          // get pagination by 10 items per page
          PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

          Page<Country> pages = countryRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

          List<CountryResponse> content = pages.getContent().stream()
                    .map(c -> mapToCountryResponse(c))
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

          return CountryResponse.builder()
                    .uuid(country.getUuid())
                    .countryName(country.getCountryName())
                    .build();
     }

     private CountryResponse mapToCountryResponse(Country c) {
          return CountryResponse.builder()
                    .countryName(c.getCountryName())
                    .uuid(c.getUuid())
                    .build();
     }
}
