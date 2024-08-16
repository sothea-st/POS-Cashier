package com.example.pos.connection1.feature.reports.report_receive;

import com.example.pos.connection1.entity.Import;
import com.example.pos.connection1.feature.imports.ImportRepository;
import com.example.pos.connection1.feature.reports.report_receive.dto.ReportReceiveResponse;
import com.example.pos.connection1.repository.UserRepository;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportReceiveServiceImp implements ReportReceiveService {

    private final ImportRepository importRepository;
    private final UserRepository userRepository;


    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String dateFrom, String dateTo, Integer receiveBy, String value) {
        validationDate(dateFrom, dateTo);
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        if (receiveBy == null) {
            Page<Import> pages = importRepository.findByDateLocalBetweenAndVendor_VendorNameContainingIgnoreCase(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    value,
                    pageRequest
            );

            return JavaCollectionResponse.builder()
                    .data(pages.getContent().stream()
                            .map(d -> {
                                String receiveByName = d.getReceiveBy() != null ? userRepository.getNameEmp(d.getReceiveBy()) : null;
                                return ReportReceiveResponse.builder()
                                        .vendorName(d.getVendor().getVendorName())
                                        .transactionNo(d.getTransactionNo())
                                        .referenceNo(d.getReferenceNo())
                                        .transactionDate(d.getImpDate())
                                        .receiveBy(receiveByName)
                                        .totalQty(d.getTotalQty())
                                        .totalCost(d.getTotal())
                                        .remark(d.getRemark())
                                        .build();
                            })
                    )
                    .count(pages.getTotalElements())
                    .build();
        }

        Page<Import> pages = importRepository.findByDateLocalBetweenAndVendor_VendorNameContainingIgnoreCaseAndReceiveBy(
                LocalDate.parse(dateFrom),
                LocalDate.parse(dateTo),
                value,
                receiveBy,
                pageRequest
        );

        return JavaCollectionResponse.builder()
                .data(pages.getContent().stream()
                        .map(d -> {
                            String receiveByName = d.getReceiveBy() != null ? userRepository.getNameEmp(d.getReceiveBy()) : null;
                            return ReportReceiveResponse.builder()
                                    .vendorName(d.getVendor().getVendorName())
                                    .transactionNo(d.getTransactionNo())
                                    .referenceNo(d.getReferenceNo())
                                    .transactionDate(d.getImpDate())
                                    .receiveBy(receiveByName)
                                    .totalQty(d.getTotalQty())
                                    .totalCost(d.getTotal())
                                    .remark(d.getRemark())
                                    .build();
                        })
                )
                .count(pages.getTotalElements())
                .build();

    }

    @Override
    public JavaCollectionResponse<?> reportReceive(Integer pageNumber, Integer pageSize, String dateFrom, String dateTo, Integer receiveBy) {
        validationDate(dateFrom, dateTo);
        Page<Import> pages = null;
        List<Import> lists = new ArrayList<>();
        List<ReportReceiveResponse> data = new ArrayList<>();
        long totalCount = 0;
        if (pageNumber != null && pageSize != null) {
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
            if (receiveBy == null) {
                pages = importRepository.findByDateLocalBetweenAndRemarkIn(LocalDate.parse(dateFrom), LocalDate.parse(dateTo),List.of("stocked","approved") ,pageRequest);
            } else {
                pages = importRepository.findByDateLocalBetweenAndReceiveByAndRemarkIn(LocalDate.parse(dateFrom), LocalDate.parse(dateTo), pageRequest, receiveBy,List.of("stocked","approved"));
            }

            totalCount = pages.getTotalElements();

            data = pages.getContent()
                    .stream()
                    .map(d -> {
                        String receiveByName = d.getReceiveBy() != null ? userRepository.getNameEmp(d.getReceiveBy()) : null;
                        return ReportReceiveResponse.builder()
                                .vendorName(d.getVendor().getVendorName())
                                .transactionNo(d.getTransactionNo())
                                .referenceNo(d.getReferenceNo())
                                .transactionDate(d.getImpDate())
                                .receiveBy(receiveByName)
                                .totalQty(d.getTotalQty())
                                .totalCost(d.getTotal())
                                .remark(d.getRemark())
                                .build();
                    })
                    .toList();
        } else {

            if (receiveBy == null) {
                lists = importRepository.findByDateLocalBetween(LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
            } else {
                lists = importRepository.findByDateLocalBetweenAndReceiveBy(LocalDate.parse(dateFrom), LocalDate.parse(dateTo), receiveBy);
            }
            totalCount = lists.size();
            data = lists.stream()
                    .map(d -> {
                        String receiveByName = d.getReceiveBy() != null ? userRepository.getNameEmp(d.getReceiveBy()) : null;
                        return ReportReceiveResponse.builder()
                                .vendorName(d.getVendor().getVendorName())
                                .transactionNo(String.valueOf(d.getId()))
                                .referenceNo(d.getReferenceNo())
                                .transactionDate(d.getImpDate())
                                .receiveBy(receiveByName)
                                .totalQty(d.getTotalQty())
                                .totalCost(d.getTotal())
                                .remark(d.getRemark())
                                .build();
                    }).toList();
        }


        return JavaCollectionResponse.builder()
                .count(totalCount)
                .data(data)
                .build();
    }


    private void validationDate(String dateFrom, String dateTo) {
        if (dateFrom == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "dateFrom can not be null.");
        if (dateTo == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "dateTo can not be null.");
        LocalDate dateFromLocal;
        LocalDate dateToLocal;

        try {
            // Parse dateFrom and dateTo from the request
            dateFromLocal = LocalDate.parse(dateFrom);
            dateToLocal = LocalDate.parse(dateTo);

            // Validate date ranges
            LocalDate currentDate = LocalDate.now();
            if (dateToLocal.isAfter(currentDate)) {
                // Throw exception if dateTo is in the future
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "The field dateTo cannot be greater than the current date: " + currentDate);
            }
            if (dateFromLocal.isAfter(dateToLocal)) {
                // Throw exception if dateFrom is after dateTo
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "The field dateFrom must be smaller than field dateTo.");
            }

        } catch (DateTimeParseException e) {
            // Handle invalid date format
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid date format. Expected format: yyyy-MM-dd", e);
        }
    }
}
