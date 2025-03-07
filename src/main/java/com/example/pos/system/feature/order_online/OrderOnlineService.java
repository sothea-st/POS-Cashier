package com.example.pos.system.feature.order_online;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.order_online.dto.OrderOnlineRequest;

public interface OrderOnlineService {

    ResponseSuccess create(OrderOnlineRequest orderOnlineRequest);

    JavaCollectionResponse<?> read(Integer pageNumber,Integer pageSize,String dateFrom,String dateTo,String orderStatus,String paymentStatus);
    JavaCollectionResponse<?> search(Integer pageNumber,Integer pageSize,String dateFrom,String dateTo,String orderStatus,String paymentStatus,String search);

}
