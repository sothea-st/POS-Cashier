package com.example.pos.system.feature.promotion;

import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.domain.User;
import com.example.pos.system.domain.promotion.Promotion;
import com.example.pos.system.domain.promotion.PromotionDetail;
import com.example.pos.system.domain.settings.Category;
import com.example.pos.system.domain.settings.Product;
import com.example.pos.system.feature.product.ProductRepository;
import com.example.pos.system.feature.promotion.dto.request.PromotionRequest;
import com.example.pos.system.feature.promotion.dto.request.PromotionStatusRequest;
import com.example.pos.system.feature.promotion.dto.response.PromotionDataDetailResponse;
import com.example.pos.system.feature.promotion.dto.response.PromotionDetailResponse;
import com.example.pos.system.feature.promotion.dto.response.PromotionResponse;
import com.example.pos.system.layer.repository.CategoryRepository;
import com.example.pos.system.layer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionServiceImp implements PromotionService {
    // inject bean repository
    private final PromotionRepository promotionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // variable not found
    private String userNotFound = "User not found with id : ";
    private String productNotFound = "Product not found with id : ";
    private String promotionNotFound = "Promotion not found with id : ";
    private String subCategoryNotFound = "Sub Category not found with  id : ";
    private String categoryNotFound = "Category not found with  id : ";
    private String departmentNotFound = "Department not found with  id : ";
    private String divisionNotFound = "Division not found with  id : ";


    @Override
    public ResponseSuccess create(PromotionRequest promotionRequest) {

        createAndUpdate(promotionRequest,null);

        return ResponseSuccess.builder().build();
    }

    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize) {

        List<PromotionResponse> list = new ArrayList<>();
        Page<Promotion> pages = null;

        long count = 0;

        if (pageNumber == null && pageSize == null) {
            pages = promotionRepository.findByStatusTrueAndIsDeletedFalse(null);
        } else {
            // sort by id
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            // create page request
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortById);
            pages = promotionRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);
        }

        list = pages.getContent().stream().map(this::mapToPromotionResponse).toList();

        count = pages.getTotalElements();
        return JavaCollectionResponse.builder()
                .count(count)
                .data(list)
                .build();

    }

    @Override
    public ResponseSuccess delete(Integer id) {
        // validate promotion
        Promotion promotion = promotionRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, promotionNotFound + id));

        promotion.setStatus(false);
        promotion.setIsDeleted(true);
        promotionRepository.save(promotion);
        return ResponseSuccess.builder().build();
    }

    @Override
    public JavaResponse<?> readById(Integer id) {
        // validate promotion
        Promotion promotion = promotionRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, promotionNotFound + id));
        return JavaResponse.builder()
                .data(mapToPromotionDetailResponse(promotion))
                .build();
    }

    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String value) {
        List<PromotionResponse> list = new ArrayList<>();
        Page<Promotion> pages = null;

        long count = 0;

        if (pageNumber == null && pageSize == null) {
            pages = promotionRepository.findByUserName(null,value);
        } else {
            // sort by id
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            // create page request
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortById);
            pages = promotionRepository.findByUserName(pageRequest,value);
        }

        list = pages.getContent().stream().map(this::mapToPromotionResponse).toList();

        count = pages.getTotalElements();
        return JavaCollectionResponse.builder()
                .count(count)
                .data(list)
                .build();
    }

    @Override
    public ResponseSuccess update(Integer id, PromotionRequest promotionRequest) {

        createAndUpdate(promotionRequest,id);

        return ResponseSuccess.builder().build();
    }

    @Override
    public ResponseSuccess updateStatus(Integer id, PromotionStatusRequest promotionStatusRequest) {

        // validate promotion
        Promotion promotion = promotionRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, promotionNotFound + id));

        promotion.setActive(promotionStatusRequest.isStatus());

        promotionRepository.save(promotion);

        return ResponseSuccess.builder().build();
    }

    private void createAndUpdate(PromotionRequest promotionRequest, Integer id) {
        // validate date
        JavaConstant.dateFromSmallerDateTo(promotionRequest.startDate(), promotionRequest.endDate());


        Promotion promotion = null;

        // update
        if (id != null) {
            // validate promotion
            promotion = promotionRepository.findByIdAndStatusTrueAndIsDeletedFalse(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, promotionNotFound + id));
            promotion.getPromotionDetails().clear(); // clear previous record
        } else {
            promotion = new Promotion();
            promotion.setActive(true);
        }

        // validate user
        User user = userRepository.findById(promotionRequest.createdBy())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, userNotFound + promotionRequest.createdBy()));

        promotion.setStatus(true);
        promotion.setIsDeleted(false);
        promotion.setCreatedBy(user);
        promotion.setPromotionType(promotionRequest.promotionType());
        promotion.setStartDate(LocalDate.parse(promotionRequest.startDate()));
        promotion.setEndDate(LocalDate.parse(promotionRequest.endDate()));
        promotion.setPercentage(promotionRequest.percentage());
        promotion.setAfterDiscount(promotionRequest.afterDiscount());
        promotion.setTotalPrice(promotionRequest.totalPrice());


        Promotion finalPromotion = promotion;

        List<PromotionDetail> promotionDetails = promotionRequest.details().stream()
                .map(item -> {

                    if (item.productId() == null) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Id can not be null!");
                    }

                    if (item.percentage() == null || item.percentage() <= 0) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "percentage can not be null and must be greater than 0 !");
                    }

                    if (item.afterDiscount() == null || item.afterDiscount().compareTo(BigDecimal.ZERO) <= 0) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "percentage can not be null and must be greater than 0 !");
                    }

                    Product product = productRepository.findByIdAndStatusTrueAndIsDeletedFalse(item.productId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, productNotFound + item.productId()));

                    return PromotionDetail.builder()
                            .promotion(finalPromotion)
                            .product(product)
                            .percentageDetail(item.percentage())
                            .afterDiscount(item.afterDiscount())
                            .build();
                }).toList();

        promotion.setPromotionDetails(promotionDetails);
        promotionRepository.save(promotion);
    }

    private PromotionDetailResponse mapToPromotionDetailResponse(Promotion promotion) {
        return PromotionDetailResponse.builder()
                .promotionType(promotion.getPromotionType())
                .startDate(promotion.getStartDate().toString())
                .endDate(promotion.getEndDate().toString())
                .percentage(promotion.getPercentage())
                .details(promotion.getPromotionDetails().stream()
                        .map(item -> {

                            Category subcategory = categoryRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(item.getProduct().getSubCategory().getId(), "subcategory")
                                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, subCategoryNotFound + item.getProduct().getSubCategory().getId()));

                            Category category = categoryRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(subcategory.getParentId(), "category")
                                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, categoryNotFound + subcategory.getParentId()));

                            Category department = categoryRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(category.getParentId(), "department")
                                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, departmentNotFound + category.getParentId()));

                            Category division = categoryRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(department.getParentId(), "division")
                                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, divisionNotFound + department.getParentId()));

                            return PromotionDataDetailResponse.builder()
                                    .barcode(item.getProduct().getBarcode())
                                    .category(category.getCatNameEn())
                                    .descEng(item.getProduct().getProNameEn())
                                    .descKhr(item.getProduct().getProNameKh())
                                    .division(division.getCatNameEn())
                                    .department(department.getCatNameEn())
                                    .percentage("%".concat(String.valueOf(item.getPercentageDetail())))
                                    .salePrice(item.getProduct().getPrice())
                                    .afterDiscount(item.getAfterDiscount())
                                    .build();
                        }).toList())
                .build();

    }

    private PromotionResponse mapToPromotionResponse(Promotion promotion) {
        String createdDate = promotion.getCreatedDate().toString().split(" ")[0];
        return PromotionResponse.builder()
                .id(promotion.getId())
                .createdDate(createdDate)
                .createdBy(promotion.getCreatedBy().getFullName())
                .promotionType(promotion.getPromotionType())
                .startDate(promotion.getStartDate().toString())
                .endDate(promotion.getEndDate().toString())
                .percentage(promotion.getPromotionType())
                .salePrice(promotion.getTotalPrice())
                .afterDiscount(promotion.getAfterDiscount())
                .isStatus(promotion.getActive())
                .build();
    }

}
