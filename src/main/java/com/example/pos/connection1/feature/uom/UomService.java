package com.example.pos.connection1.feature.uom;

import com.example.pos.connection1.feature.uom.dto.UomRequest;
import com.example.pos.connection1.feature.uom.dto.UomResponse;
import com.example.pos.connection1.feature.uom.dto.UomUpdateRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface UomService {

    /*
      * read all uom 
      * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0 
      * value was given from controller
    */
    JavaCollectionResponse<?> read (int pageSize, int pageNumber); 

    /*
      * read vendor by id
      * required paramater id
    */
    UomResponse readById (Integer id);

    /*
      * create new uom 
      * required paramater uomrequest
    */
    UomResponse create(UomRequest uomRequest);

    /*
      * update uom by id
      * required paramater id , UomUpdateRequest
    */
    UomResponse updateById(Integer id, UomUpdateRequest uomUpdateRequest);
    
    /*
      * delete uom by id
    */
    void deleteById(Integer id);

    /*
      * read search uom 
      * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0 
      * value was given from controller
    */
    JavaCollectionResponse<?> search(int pageSize, int pageNumber, String Value);

}
