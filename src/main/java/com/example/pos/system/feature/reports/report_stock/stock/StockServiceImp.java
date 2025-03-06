package com.example.pos.system.feature.reports.report_stock.stock;

import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.domain.settings.Product;
import com.example.pos.system.domain.settings.Status;
import com.example.pos.system.feature.product.ProductRepository;
import com.example.pos.system.feature.reports.report_stock.stock.dto.StockResponse;
import com.example.pos.system.feature.status.StatusRepository;
import com.example.pos.system.layer.repository.ImportDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImp implements StockService {
    // inject bean repository
    private final ProductRepository productRepository;
    private final ImportDetailRepository importDetailRepository;
    private final StatusRepository statusRepository;

    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize, Integer statusId) {

        List<StockResponse> lists = new ArrayList<>();
        long count = 0;

        Page<Product> pages = null;

        Status status = statusRepository.findByIdAndStatusTrueAndIsDeletedFalse(statusId)
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND , "Status not found with id : " + statusId));

        if (pageNumber == null && pageSize == null) {
            pages = productRepository.findByStatusTrueAndIsDeletedFalseAndProductActive(null,status);
        } else {
            Sort sortById = Sort.by("id");
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortById);
            pages = productRepository.findByStatusTrueAndIsDeletedFalseAndProductActive(pageRequest,status);
        }

        lists = pages.getContent().stream()
                .map(product -> StockResponse.builder()
                        .productName(product.getProNameEn())
                        .productImage(product.getProImageName())
                        .categoryName(product.getSubCategory().getCatNameEn())
                        .supplierName(product.getVendor().getVendorName())
                        .price(product.getPrice())
                        .cost(product.getCost())
                        .qty(getQty(product.getId()))
                        .build()).toList();

        count = pages.getTotalElements();


        return JavaCollectionResponse.builder()
                .count(count)
                .data(lists)
                .build();
    }


    private Integer getQty(Integer productId) {
        return importDetailRepository.sumQtyByProId(productId) == null ? 0 : importDetailRepository.sumQtyByProId(productId);
    }
}

