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
     JavaCollectionResponse<?> read(int pageNumber, int pageSize);

     /*
      * read country
      * required paramater id
      */
     CountryResponse readById(int id);

     /*
      * update country  
      * required paramater id and CountryRequest
      */
     CountryResponse updateById(int id,CountryUpdateRequest countryUpdateRequest);

     /*
      * delete country by id
      * required paramater id  
      */
     void deleteById(int id);


     /*
      * read search country
      * required paramater searchValue
      */
     JavaCollectionResponse<?> search (int pageNumber, int pageSize, String searchValue);


}
