package com.example.pos.system.feature.adjustment;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.adjustment.dto.AdjustmentRequest;
import com.example.pos.system.feature.adjustment.dto.UpdateRequest;

public interface AdjustmentService {

    ResponseSuccess create(AdjustmentRequest adjustmentRequest);

    JavaCollectionResponse<?> read(Integer pageNumber , Integer pageSize);

    JavaResponse<?> readById(Integer id);

    ResponseSuccess update(Integer id ,AdjustmentRequest adjustmentRequest );

    ResponseSuccess delete(Integer id);

    JavaCollectionResponse<?> search(String dateFrom , String dateTo,Integer pageSize, Integer pageNumber, String valueSearch);

    JavaCollectionResponse<?> filter(String dateFrom , String dateTo,Integer pageSize, Integer pageNumber, String transaction,String status,Integer reason);

    ResponseSuccess updateStatus(Integer id , UpdateRequest updateRequest);

}
