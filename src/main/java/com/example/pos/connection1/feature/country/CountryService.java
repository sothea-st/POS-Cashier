package com.example.pos.connection1.feature.country;

import com.example.pos.connection1.feature.country.dto.CountryRequest;
import com.example.pos.connection1.feature.country.dto.CountryResponse;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface CountryService {
     /*
      * create new country
      * retuqired paramater CountryRequest
      */
     CountryResponse create(CountryRequest countryRequest);

     /*
      * read all country
      * retuqired paramater CountryRequest
      */
     JavaCollectionResponse<?> read(int pageNumber, int pageSize);

}
