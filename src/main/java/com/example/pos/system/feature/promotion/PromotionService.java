package com.example.pos.system.feature.promotion;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.promotion.dto.request.ListCategoryRequest;
import com.example.pos.system.feature.promotion.dto.request.PromotionRequest;
import com.example.pos.system.feature.promotion.dto.request.PromotionStatusRequest;

public interface PromotionService {

    ResponseSuccess create(PromotionRequest promotionRequest);

    JavaCollectionResponse<?> read(Integer pageNumber ,Integer pageSize);

    ResponseSuccess delete(Integer id);

    JavaResponse<?> readById(Integer id);

    ResponseSuccess update(Integer id , PromotionRequest promotionRequest);

    JavaCollectionResponse<?> search(Integer pageNumber , Integer pageSize , String value);

    ResponseSuccess updateStatus(Integer id, PromotionStatusRequest promotionStatusRequest);

    JavaCollectionResponse<?> readProductByCategory(ListCategoryRequest listCategoryRequest);


}
