package com.example.pos.connection1.feature.status;

import com.example.pos.connection1.feature.status.dto.StatusRequest;
import com.example.pos.connection1.feature.status.dto.StatusResponse;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

public interface StatusService {
    StatusResponse readById (Integer id);

    JavaCollectionResponse<?> read (int pageSize, int pageNumber);

    StatusResponse create (StatusRequest statusRequest);

    StatusResponse update (Integer id, StatusRequest statusRequest);

    void delete (Integer id);

}
 