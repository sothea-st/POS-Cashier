package com.example.pos.system.feature.reports.report_product_stock_in;

import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.domain.stock.Import;
import com.example.pos.system.feature.imports.ImportRepository;
import com.example.pos.system.feature.reports.report_stock.stock.dto.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportProductStockInServiceImp implements  ReportProductStockInService{

    private final ImportRepository importRepository;

    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize, String dateFrom, String dateTo) {

        // validate date
        JavaConstant.validationDate( dateFrom,dateTo);

        Sort sortById = Sort.by(Sort.Direction.DESC,"id");
        // pagination
        PageRequest pageRequest = PageRequest.of(pageNumber -1,pageSize,sortById);

        Page<Import> pages = importRepository.findByStatusTrueAndIsDeletedFalseAndDateLocalBetween(LocalDate.parse(dateFrom),LocalDate.parse(dateTo),pageRequest);

//        List<StockResponse> lists  = pages.getContent().stream()
//                .map(item -> StockResponse.builder()
//                        .productName(item.getIm.getProNameEn())
//                        .productImage(product.getProImageName())
//                        .categoryName(product.getSubCategory().getCatNameEn())
//                        .supplierName(product.getVendor().getVendorName())
//                        .price(product.getPrice())
//                        .cost(product.getCost())
//                        .qty(getQty(product.getId()))
//                        .build()).toList();

        long count = pages.getTotalElements();
//        return JavaCollectionResponse.builder()
//                .count(count)
//                .data(lists)
//                .build();



        return null;
    }
}
