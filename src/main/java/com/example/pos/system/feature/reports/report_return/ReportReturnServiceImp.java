package com.example.pos.system.feature.reports.report_return;

import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.constant.util.collection_response.JavaCollectionResponse;
import com.example.pos.system.feature.reports.report_return.dto.ReportReturnProjection;
import com.example.pos.system.layer.repository.sourceDataRepository.ReturnProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportReturnServiceImp implements ReportReturnService {
    // inject bean repository
    private final ReturnProductRepository returnProductRepository;

    @Override
    public JavaCollectionResponse<?> search(String dateFrom, String dateTo, String search) {

        // validate date from and date to
        JavaConstant.validationDate(dateFrom,dateTo);

        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo);
        List<ReportReturnProjection> data = new ArrayList<>();
        Integer count = returnProductRepository.getCountResultSearch(dateFromLocalDate, dateToLocalDate,search);

        count = count == null ? 0 : count;

        data = returnProductRepository.searchReportReturn(dateFromLocalDate, dateToLocalDate,search);

        return JavaCollectionResponse.builder()
                .data(data)
                .count(count)
                .build();
    }

    @Override
    public JavaCollectionResponse<?> reportReturn(String dateFrom, String dateTo, Integer pageSize, Integer pageNumber) {

        // validate date from and date to
        JavaConstant.validationDate(dateFrom,dateTo);

        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo);
        List<ReportReturnProjection> data = new ArrayList<>();
        if (pageNumber == null && pageSize == null) {
            data = returnProductRepository.getReportReturns(dateFromLocalDate, dateToLocalDate);
        } else {
            pageNumber = (pageNumber-1) * 10;
            data = returnProductRepository.getReportReturn(dateFromLocalDate, dateToLocalDate, pageSize, pageNumber);
        }

        Integer count = returnProductRepository.getCountResult(dateFromLocalDate, dateToLocalDate);

        count = count == null ? 0 : count;
        return JavaCollectionResponse.builder()
                .data(data)
                .count(count)
                .build();
    }
}
