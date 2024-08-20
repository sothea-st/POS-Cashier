package com.example.pos.connection1.feature.status;

import com.example.pos.connection1.feature.status.dto.StatusRequest;
import com.example.pos.connection1.feature.status.dto.StatusResponse;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface StatusService {
    
    /*
      * read status by id
      * required paramater id
    */
    StatusResponse readById (Integer id);

    /*
      * read all status 
      * paramater pageSize and pageNumber optional pageNumber = 10 , pageSize = 0 
      * value was given from controller
    */
    JavaCollectionResponse<?> read (Integer pageSize, Integer pageNumber);

    /*
      * create new status 
      * required paramater statusRequest
    */
    StatusResponse create (StatusRequest statusRequest);

    /*
      * update status by id
      * required paramater id , statusRequest
    */
    StatusResponse update (Integer id, StatusRequest statusRequest);

    /*
      * delete status by id
    */
    void delete (Integer id);

    /*
      * Search status
    */
    JavaCollectionResponse<?> search(Integer pageSize, Integer pageNumber, String searchValue);

}
 