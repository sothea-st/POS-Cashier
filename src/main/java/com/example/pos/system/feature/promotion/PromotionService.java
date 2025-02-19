package com.example.pos.system.feature.promotion;

import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.feature.promotion.dto.PromotionRequest;

public interface PromotionService {

    ResponseSuccess create(PromotionRequest promotionRequest);

}
