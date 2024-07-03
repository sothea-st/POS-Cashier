package com.example.pos.connection1.feature.attribute;

import com.example.pos.connection1.feature.attribute.dto.AttributeRequest;
import com.example.pos.connection1.feature.attribute.dto.AttributeResponse;
import com.example.pos.connection1.feature.attribute.dto.AttributeUpdateRequest;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface AttributeService {

    /*
      * read attribute by id
      * required paramater id
    */
    AttributeResponse readById(Integer id);

    /*
      * read all attribute 
      * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0 
      * value was given from controller
    */
    JavaCollectionResponse<?> read(int pageSize, int pageNumber);

    /*
      * create new attribute 
      * required paramater attributeRequest
    */
    AttributeResponse create(AttributeRequest attributeRequest);

    /*
      * update attribute by id
      * required paramater id , attributeUpdateRequest
    */
    AttributeResponse updateById(Integer id, AttributeUpdateRequest attributeUpdateRequest);

    /*
      * delete attribute by id
    */
    void deleteById(Integer id);

}
