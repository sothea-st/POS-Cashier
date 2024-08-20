package com.example.pos.connection1.feature.vendor;

import com.example.pos.connection1.feature.vendor.dto.VendorRequest;
import com.example.pos.connection1.feature.vendor.dto.VendorResponse;
import com.example.pos.connection1.feature.vendor.dto.VendorUpdateRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface VendorService {
     /*
      * create new vendor 
      * required paramater VendorRequest
      */
     VendorResponse create(VendorRequest vendorRequest);
      /*
      * read all vendor 
      * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0 
      * value was given from controller
      */
     JavaCollectionResponse<?> read(Integer pageSize,Integer pageNumber);
     /*
      * read vendor by id
      * required paramater id
      */
     VendorResponse readByUuid(Integer id);

     /*
      * delete vendor by id paramater
      */
     void delete(Integer id);
      /*
      * update vendor by id
      * required paramater id , VendorUpdateRequest
      */
      VendorResponse updateByUuid(Integer id,VendorUpdateRequest vendorUpdateRequest);

      /*
      * read Search vendor 
      * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0 
      * value was given from controller
      */
      JavaCollectionResponse<?> search(Integer pageSize, Integer pageNumber, String searchValue);
}
