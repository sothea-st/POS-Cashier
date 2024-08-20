package com.example.pos.connection1.feature.country;

import com.example.pos.connection1.feature.country.dto.CountryRequest;
import com.example.pos.connection1.feature.country.dto.CountryResponse;
import com.example.pos.connection1.feature.country.dto.CountryUpdateRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface CountryService {
     /*
      * create new country
      * required paramater CountryRequest
      */
     CountryResponse create(CountryRequest countryRequest);

     /*
      * read all country
      * required paramater CountryRequest
      */
     JavaCollectionResponse<?> read(Integer pageSize, Integer pageNumber);

     /*
      * read country
      * required paramater id
      */
     CountryResponse readById(Integer id);

     /*
      * update country  
      * required paramater id and CountryRequest
      */
     CountryResponse updateById(Integer id,CountryUpdateRequest countryUpdateRequest);

     /*
      * delete country by id
      * required paramater id  
      */
     void deleteById(Integer id);


     /*
      * read search country
      * required paramater searchValue
      */
     JavaCollectionResponse<?> search (Integer pageSize, Integer pageNumber, String searchValue);


}
