package com.example.pos.system.feature.reports.report_product_stock_in;

import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.feature.imports.ImportRepository;
import com.example.pos.system.feature.reports.report_stock.stock.dto.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportProductStockInServiceImp implements ReportProductStockInService {

    private final ImportRepository importRepository;

    @Override
    public JavaCollectionResponse<?> read(Integer pageNumber, Integer pageSize, String dateFrom, String dateTo) {
        return getResponse(pageNumber, pageSize, dateFrom, dateTo, null);
    }

    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String dateFrom, String dateTo, String search) {
        return getResponse(pageNumber, pageSize, dateFrom, dateTo, search);
    }

    private JavaCollectionResponse<?> getResponse(Integer pageNumber, Integer pageSize, String dateFrom, String dateTo, String search) {
        JavaConstant.validationDate(dateFrom, dateTo);

        List<StockResponse> data = importRepository.getReportProductStockIn(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageNumber,
                        pageSize,
                        search
                ).stream()
                .map(item -> StockResponse.builder()
                        .productName(item.getProduct_name())
                        .categoryName(item.getCategory_name())
                        .supplierName(item.getSupplier_name())
                        .price(item.getPrice())
                        .qty(item.getQty())
                        .date(item.getDate())
                        .description(item.getDescription())
                        .build())
                .toList();

        long count = importRepository.countReportProductStockIn(
                LocalDate.parse(dateFrom),
                LocalDate.parse(dateTo),
                search
        );

        return JavaCollectionResponse.builder()
                .count(count)
                .data(data)
                .build();
    }



}
