package com.example.pos.connection1.feature.vendor;

import java.util.List;
import java.util.Map;
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
     JavaCollectionResponse<?> read(int pageSize,int pageNumber);
     /*
      * read vendor by id
      * required paramater id
      */
     VendorResponse readByUuid(int id);

     /*
      * delete vendor by id paramater
      */
     void delete(int id);
      /*
      * update vendor by id
      * required paramater id , VendorUpdateRequest
      */
      VendorResponse updateByUuid(int id,VendorUpdateRequest vendorUpdateRequest);
}
