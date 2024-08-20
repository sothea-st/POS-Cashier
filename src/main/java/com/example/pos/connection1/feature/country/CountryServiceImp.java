package com.example.pos.connection1.feature.country;

import java.util.List;

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
     private String nameAlreadyExisted = "The Country Name is already existed.";

     // Delete Country by id
     @Override
     public void deleteById(Integer id) {
          Country country = countryRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
          countryRepository.delete(country);
     }

     // Update Country by id
     @Override
     public CountryResponse updateById(Integer id, CountryUpdateRequest countryUpdateRequest) {
          Country country = countryRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));

          if (!countryUpdateRequest.countryName().equals(country.getCountryName())) {
               // validate name already exist
               if (countryRepository.existsByCountryName(countryUpdateRequest.countryName())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, nameAlreadyExisted);
               }
          }

          country.setCountryName(countryUpdateRequest.countryName());
          country.setUuid(countryUpdateRequest.uuid());
          countryRepository.save(country);
          return mapToCountryResponse(country);
     }

     // Get Country by id
     @Override
     public CountryResponse readById(Integer id) {
          Country country = countryRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, idNotFound));
          return mapToCountryResponse(country);
     }

     // Get List Country
     @Override
     public JavaCollectionResponse<?> read(Integer pageSize, Integer pageNumber) {

          List<CountryResponse> data = null;

          if (pageNumber == null && pageSize == null) {
               data = countryRepository.findByStatusTrueAndIsDeletedFalse().stream()
                         .map(this::mapToCountryResponse)
                         .toList();

               return JavaCollectionResponse.builder()
                         .count(data.size())
                         .data(data)
                         .build();

          } else {
               // sort list by id desc
               Sort sortById = Sort.by(Sort.Direction.DESC, "id");
               // get pagination by 10 items per page
               PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

               Page<Country> pages = countryRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

               List<CountryResponse> content = pages.getContent().stream()
                         .map(this::mapToCountryResponse)
                         .toList();

               return JavaCollectionResponse.builder()
                         .data(content)
                         .count(pages.getTotalElements())
                         .build();
          }
     }

     // Create new country
     @Override
     public CountryResponse create(CountryRequest countryRequest) {

          // validate name already exist
          if (countryRepository.existsByCountryName(countryRequest.countryName())) {
               throw new ResponseStatusException(
                         HttpStatus.CONFLICT, nameAlreadyExisted);
          }

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

     // Response
     private CountryResponse mapToCountryResponse(Country c) {
          return CountryResponse.builder()
                    .countryName(c.getCountryName())
                    .id(c.getId())
                    .uuid(c.getUuid())
                    .build();
     }

     // Search country by country name
     @Override
     public JavaCollectionResponse<?> search(Integer pageSize, Integer pageNumber, String searchValue) {

          List<CountryResponse> data = null;

          if (pageNumber == null && pageSize == null) {
               data = countryRepository.findByCountryName(searchValue).stream()
                         .map(this::mapToCountryResponse)
                         .toList();

               return JavaCollectionResponse.builder()
                         .count(data.size())
                         .data(data)
                         .build();

          } else {

               Sort sortById = Sort.by(Sort.Direction.DESC, "id");
               PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
               Page<Country> pages = countryRepository.findByCountryName(pageRequest, searchValue);

               List<CountryResponse> content = pages.getContent()
                         .stream()
                         .map(c -> mapToCountryResponse(c))
                         .toList();

               return JavaCollectionResponse.builder()
                         .count(pages.getTotalElements())
                         .data(content)
                         .build();
          }
     }

}
