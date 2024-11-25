package com.example.pos.system.feature.attribute;

import com.example.pos.system.feature.attribute.dto.AttributeRequest;
import com.example.pos.system.feature.attribute.dto.AttributeResponse;
import com.example.pos.system.feature.attribute.dto.AttributeUpdateRequest;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;

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
    JavaCollectionResponse<?> read(Integer pageSize, Integer pageNumber);

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

    /*
      * Search attribute
    */
    JavaCollectionResponse<?> search (Integer pageSize, Integer pageNumber, String valueSearch);

}
