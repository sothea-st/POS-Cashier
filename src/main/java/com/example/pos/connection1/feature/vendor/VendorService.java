package com.example.pos.connection1.feature.vendor;

import org.springframework.data.domain.Page;
import com.example.pos.connection1.feature.vendor.dto.VendorRequest;
import com.example.pos.connection1.feature.vendor.dto.VendorResponse;
import com.example.pos.connection1.feature.vendor.dto.VendorUpdateRequest;

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
     Page<VendorResponse> read(int pageSize,int pageNumber);
     /*
      * read vendor by uuid
      * required paramater uuid
      */
     VendorResponse readByUuid(String uuid);

     /*
      * delete vendor by uuid paramater
      */
     void delete(String uuid);
      /*
      * update vendor by uuid
      * required paramater uuid , VendorUpdateRequest
      */
      VendorResponse updateByUuid(String uuid,VendorUpdateRequest vendorUpdateRequest);
}
