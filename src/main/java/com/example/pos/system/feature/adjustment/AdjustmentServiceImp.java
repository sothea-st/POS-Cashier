package com.example.pos.system.feature.adjustment;

import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.constant.util.response_success.JavaResponse;
import com.example.pos.system.constant.util.response_success.ResponseSuccess;
import com.example.pos.system.domain.User;
import com.example.pos.system.domain.adjustment.Adjustment;
import com.example.pos.system.domain.adjustment.AdjustmentDetail;
import com.example.pos.system.domain.settings.Attribute;
import com.example.pos.system.domain.settings.Category;
import com.example.pos.system.domain.settings.Product;
import com.example.pos.system.domain.sourceData.Reason;
import com.example.pos.system.feature.adjustment.dto.AdjustmentRequest;
import com.example.pos.system.feature.adjustment.dto.UpdateRequest;
import com.example.pos.system.feature.adjustment.dto.response.AdjustmentResponse;
import com.example.pos.system.feature.adjustment.dto.response.AdjustmentResponseDetail;
import com.example.pos.system.feature.adjustment.dto.response.ReasonData;
import com.example.pos.system.feature.adjustment.dto.response.ResponseAdjustmentDetail;
import com.example.pos.system.feature.attribute.dto.AttributeResponse;
import com.example.pos.system.feature.product.ProductRepository;
import com.example.pos.system.layer.repository.CategoryRepository;
import com.example.pos.system.layer.repository.ImportDetailRepository;
import com.example.pos.system.layer.repository.UserRepository;
import com.example.pos.system.layer.repository.sourceDataRepository.ReasonRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdjustmentServiceImp implements AdjustmentService {
    // inject bean repository
    private final AdjustmentRepository adjustmentRepository;
    private final ReasonRepository reasonRepository;
    private final ProductRepository productRepository;
    private final ImportDetailRepository importDetailRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    // variable not found
    private String reasonNotFound = "Reason not found with id : ";
    private String productNotFound = "Product not found with id : ";
    private String qtyMustBeGreaterThan0 = "Qty must be greater than 0 !";
    private String adjustmentNotFound = "Adjustment not found with id : ";
    private String usertNotFound = "User not found with id : ";


    @Override
    public ResponseSuccess create(AdjustmentRequest adjustmentRequest) {

        // validate reason
        Reason reason = reasonRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(adjustmentRequest.reasonId(), "Adjustment")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, reasonNotFound + adjustmentRequest.reasonId()));

        long count = adjustmentRepository.count();
        count++;
        String transaction = "ADJ101-" + JavaConstant.codeAdjustment((int) count);

        Adjustment adjustment = Adjustment.builder()
                .reason(reason)
                .reference(adjustmentRequest.reference())
                .transactionDate(LocalDate.parse(adjustmentRequest.transactionDate()))
                .comment(adjustmentRequest.comment())
                .transaction(transaction)
                .totalQty(adjustmentRequest.totalQty())
                .totalCost(adjustmentRequest.totalCost())
                .status("Draft")
                .isDeleted(true)
                .build();

        List<AdjustmentDetail> adjustmentDetails = adjustmentRequest.details().stream()
                .map(value -> {

                    if (value.productId() == null) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Id can not be null!");
                    }

                    if (value.qty() <= 0) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "at productId : " + value.productId() + " " + qtyMustBeGreaterThan0);
                    }

                    Product product = productRepository.findByIdAndStatusTrueAndIsDeletedFalse(value.productId()).orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, productNotFound + value.productId()));

                    // get sub category
                    Category subCategory = categoryRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(product.getSubCategory().getId(),"subcategory")
                            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Sub Category not found with id : " + product.getSubCategory().getId()) );

                    // get category
                    Category category = categoryRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(subCategory.getParentId(),"category")
                            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Category not found with id : " + subCategory.getParentId()) );

                    // get department
                    Category department = categoryRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(category.getParentId(),"department")
                            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Department not found with id : " + category.getParentId()) );



                    // get division
                    Category division = categoryRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(department.getParentId(),"division")
                            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Division not found with id : " + department.getParentId()) );

                    return AdjustmentDetail.builder()
                            .adjustment(adjustment)  // Now adjustment is initialized
                            .qty(value.qty())
                            .product(product)
                            .subCategory(subCategory)
                            .category(category)
                            .department(department)
                            .division(division)
                            .build();

                })
                .toList();
        adjustment.setAdjustmentDetails(adjustmentDetails);

        adjustmentRepository.save(adjustment);

        return ResponseSuccess.builder().build();
    }

    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize) {

        List<AdjustmentResponse> list = new ArrayList<>();
        Page<Adjustment> pages = null;

        long count = 0;

        if (pageNumber == null && pageSize == null) {
            pages = adjustmentRepository.findByIsDeletedTrue(null);
        } else {
            // sort by id
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            // create page request
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortById);
            pages = adjustmentRepository.findByIsDeletedTrue(pageRequest);
        }

        list = pages.getContent().stream().map(this::mapToAdjustmentResponse).toList();

        count = pages.getTotalElements();
        return JavaCollectionResponse.builder()
                .count(count)
                .data(list)
                .build();
    }

    @Override
    public JavaResponse<?> readById(Integer id) {

        // validate adjustment
        Adjustment adjustment = adjustmentRepository.findByIdAndIsDeletedTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, adjustmentNotFound + id));


        return JavaResponse.builder()
                .data(mapToAdjustmentResponseDetail(adjustment))
                .build();
    }

    @Transactional
    @Override
    public ResponseSuccess update(Integer id, AdjustmentRequest adjustmentRequest) {
        // validate reason
        Reason reason = reasonRepository.findByIdAndStatusTrueAndIsDeletedFalseAndCode(adjustmentRequest.reasonId(), "Adjustment")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, reasonNotFound + adjustmentRequest.reasonId()));


        // validate adjustment
        Adjustment adjustment = adjustmentRepository.findByIdAndIsDeletedTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, adjustmentNotFound + id));

        adjustmentRepository.delete(adjustment); // delete


        // call create function to create new
        return create(adjustmentRequest);
    }

    @Override
    public ResponseSuccess delete(Integer id) {
        // validate adjustment
        Adjustment adjustment = adjustmentRepository.findByIdAndIsDeletedTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, adjustmentNotFound + id));
        adjustment.setIsDeleted(false);
        adjustmentRepository.save(adjustment);
        return ResponseSuccess.builder().build();
    }

    @Override
    public JavaCollectionResponse<?> search(String dateFrom, String dateTo, Integer pageSize, Integer pageNumber, String valueSearch) {

        // validate date
        JavaConstant.validationDate(dateFrom, dateTo);

        List<AdjustmentResponse> data = null;
        Page<Adjustment> pages = null;
        long count = 0;
        PageRequest pageRequest = null;


        if (pageNumber == null && pageSize == null) {
            pageRequest = null;
        } else {
            Sort sortById = Sort.by(Sort.Direction.DESC, "id"); // sort by id DESC
            pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortById); // pageNumber start:0,1,2,3...
        }

        pages = adjustmentRepository.searchAdjustment(
                pageRequest,
                valueSearch,
                LocalDate.parse(dateFrom),
                LocalDate.parse(dateTo)
        );

        count = pages.getTotalElements();
        data = pages.getContent().stream()
                .map(this::mapToAdjustmentResponse).toList();

        return JavaCollectionResponse.builder()
                .count(count)
                .data(data)
                .build();
    }

    @Override
    public JavaCollectionResponse<?> filter(String dateFrom, String dateTo, Integer pageSize, Integer pageNumber, String transaction, String status, Integer reasonId) {
        // Validate date format
        JavaConstant.validationDate(dateFrom, dateTo);

        List<AdjustmentResponse> data = null;
        Page<Adjustment> pages = null;
        long count = 0;
        PageRequest pageRequest = null;

        // Handle pageRequest if pageSize or pageNumber is provided
        if (pageNumber != null && pageSize != null) {
            Sort sortById = Sort.by(Sort.Direction.DESC, "transactionDate"); // Sort by ID in descending order
            pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortById); // Page number starts from 0, so subtract 1
        }

        // Parse the dates
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom);
        LocalDate parsedDateTo = LocalDate.parse(dateTo);


        if( transaction == null && status == null && reasonId == null ) {
            pages = adjustmentRepository.filter(pageRequest,parsedDateFrom, parsedDateTo);
        } else if ( transaction != null && status == null && reasonId == null ) {
            pages = adjustmentRepository.filter(pageRequest,parsedDateFrom, parsedDateTo,transaction);
        } else if ( transaction == null && status != null && reasonId == null ) {
            pages = adjustmentRepository.filterStatus(pageRequest,parsedDateFrom, parsedDateTo,status);
        } else if ( transaction == null && status == null && reasonId != null ) {
            pages = adjustmentRepository.filterReason(pageRequest,parsedDateFrom, parsedDateTo,reasonId);
        } else if ( transaction != null && status != null && reasonId == null ) {
            pages = adjustmentRepository.filter(pageRequest,parsedDateFrom, parsedDateTo,transaction,status);
        } else if ( transaction != null && status == null && reasonId != null ) {
            pages = adjustmentRepository.filter(pageRequest,parsedDateFrom, parsedDateTo,transaction,reasonId);
        } else if ( transaction == null && status != null && reasonId != null ) {
            pages = adjustmentRepository.filterStatus(pageRequest,parsedDateFrom, parsedDateTo,status,reasonId);
        }else {
            pages = adjustmentRepository.filter(pageRequest,parsedDateFrom, parsedDateTo,transaction,status,reasonId);
        }

        // Count total elements and fetch the content
        count = pages.getTotalElements();
        data = pages.getContent().stream()
                .map(this::mapToAdjustmentResponse) // Assuming mapToAdjustmentResponse is implemented correctly
                .toList();

        // Return response
        return JavaCollectionResponse.builder()
                .count(count)
                .data(data)
                .build();
    }

    @Override
    public ResponseSuccess updateStatus(Integer id , UpdateRequest updateRequest) {
        // validate adjustment
        Adjustment adjustment = adjustmentRepository.findByIdAndIsDeletedTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, adjustmentNotFound + id));

        User user = userRepository.findById(updateRequest.approvalBy())
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND , usertNotFound + updateRequest.approvalBy()) );

        adjustment.setApproveBy(user);
        adjustment.setPostDate(LocalDate.now());
        adjustment.setStatus(updateRequest.status());
        adjustmentRepository.save(adjustment);
        return ResponseSuccess.builder().build();
    }

    private AdjustmentResponse mapToAdjustmentResponse(Adjustment adjustment) {
        return AdjustmentResponse.builder()
                .id(adjustment.getId())
                .transaction(adjustment.getTransaction())
                .transactionDate(adjustment.getTransactionDate().toString())
                .postDate(adjustment.getPostDate() == null ? null : adjustment.getPostDate().toString())
                .referenceName(adjustment.getReference())
                .approvalUser(adjustment.getApproveBy() == null ? null : adjustment.getApproveBy().getFullName())
                .reason(adjustment.getReason().getReason())
                .totalQty(adjustment.getTotalQty())
                .totalCost(adjustment.getTotalCost())
                .status(adjustment.getStatus())
                .build();
    }

    private AdjustmentResponseDetail mapToAdjustmentResponseDetail(Adjustment adjustment) {
        return AdjustmentResponseDetail.builder()
                .reason(ReasonData.builder()
                        .id(adjustment.getReason().getId())
                        .name(adjustment.getReason().getReason())
                        .build())
                .reference(adjustment.getReference())
                .transactionDate(adjustment.getTransactionDate().toString())
                .comment(adjustment.getComment())
                .totalQty(adjustment.getTotalQty())
                .totalCost(adjustment.getTotalCost())
                .transaction(adjustment.getTransaction())
                .details(adjustment.getAdjustmentDetails().stream()
                        .map(val -> ResponseAdjustmentDetail.builder()
                                .productId(val.getProduct().getId())
                                .itemCode(val.getProduct().getItemCode())
                                .barcode(val.getProduct().getBarcode())
                                .productNameEn(val.getProduct().getProNameEn())
                                .productNameKh(val.getProduct().getProNameKh())
                                .oum(val.getProduct().getUom().getUomNameEn())
                                .onHandQty(importDetailRepository.sumQtyByProId(val.getProduct().getId()))
                                .adjustQty(val.getQty())
                                .cost(val.getProduct().getCost())
                                .build()).toList()
                )
                .build();
    }

}
