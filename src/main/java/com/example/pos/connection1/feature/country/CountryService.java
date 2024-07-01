package com.example.pos.connection1.feature.country;

import com.example.pos.connection1.feature.country.dto.CountryRequest;
import com.example.pos.connection1.feature.country.dto.CountryResponse;
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
      * read  country
      * required paramater uuid
      */
     CountryResponse readByUuid(String uuid);

     /*
      * update country updateByUuid
      * required paramater uuid and CountryRequest
      */
     CountryResponse updateByUuid(String uuid,CountryRequest countryRequest);

     /*
      * delete country by uuid
      * required paramater uuid  
      */
     void deleteByUuid(String uuid);

}
