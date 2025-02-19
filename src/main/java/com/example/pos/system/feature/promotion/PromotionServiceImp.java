package com.example.pos.system.feature.promotion;

import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.domain.User;
import com.example.pos.system.domain.promotion.Promotion;
import com.example.pos.system.domain.promotion.PromotionDetail;
import com.example.pos.system.domain.settings.Product;
import com.example.pos.system.feature.product.ProductRepository;
import com.example.pos.system.feature.promotion.dto.PromotionDetailRequest;
import com.example.pos.system.feature.promotion.dto.PromotionRequest;
import com.example.pos.system.layer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionServiceImp implements PromotionService{
    // inject bean repository
    private final PromotionRepository promotionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // variable not found
    private String userNotFound = "User not found with id : ";
    private String productNotFound = "Product not found with id : ";
    @Override
    public ResponseSuccess create(PromotionRequest promotionRequest) {

        // validate date
        JavaConstant.validationDate(promotionRequest.startDate(),promotionRequest.endDate());

        // validate user
        User user = userRepository.findById(promotionRequest.createdBy())
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,userNotFound+promotionRequest.createdBy()));

        Promotion promotion = new Promotion();
        promotion.setStatus(true);
        promotion.setIsDeleted(false);
        promotion.setCreatedBy(user);
        promotion.setPromotionType(promotionRequest.promotionType());
        promotion.setStartDate(LocalDate.parse(promotionRequest.startDate()));
        promotion.setEndDate(LocalDate.parse(promotionRequest.endDate()));
        promotion.setPercentage(promotionRequest.percentage());
        promotion.setActive(true);
        promotion.setAfterDiscount(promotionRequest.afterDiscount());
        promotion.setTotalPrice(promotionRequest.totalPrice());

        List<PromotionDetail> promotionDetails =  promotionRequest.details().stream()
                .map(item -> {
                    Product product = productRepository.findByIdAndStatusTrueAndIsDeletedFalse(item.productId())
                            .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND , productNotFound + item.productId()) );
                    return PromotionDetail.builder()
                            .promotion(promotion)
                            .product(product)
                            .percentageDetail(item.percentage())
                            .afterDiscount(item.afterDiscount())
                            .build();
                }).toList();
        promotion.setPromotionDetails(promotionDetails);
        promotionRepository.save(promotion);

        return ResponseSuccess.builder().build();
    }
}
