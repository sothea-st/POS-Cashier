package com.example.pos.connection1.feature.reports.report_purchase_order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.pos.connection1.entity.Import;
import com.example.pos.connection1.entity.User;
import com.example.pos.connection1.feature.imports.ImportRepository;
import com.example.pos.connection1.feature.reports.report_purchase_order.dto.ReportPOResponse;
import com.example.pos.connection1.feature.reports.report_purchase_order.dto.ReportPurchaseOrderRequest;
import com.example.pos.connection1.feature.reports.report_purchase_order.dto.ReportPurchaseOrderResponse;
import com.example.pos.connection1.mapper.ImportMapper;
import com.example.pos.connection1.repository.UserRepository;
import com.example.pos.connection1.util.collection_response.JavaCollectionResponse;

import java.util.*;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class ReportPuchaseOrderServiceImp implements ReportPurchaseOrderService {

    private final ImportRepository importRepository;
    private final ImportMapper importMapper;
    private final UserRepository userRepository;


    @Override
    public JavaCollectionResponse<?> search(Integer pageNumber, Integer pageSize, String dateFrom, String dateTo, Integer requestId, Integer checkId, Integer approvedId, Integer rejectId, String remark , String search) {
        validationDate(dateFrom, dateTo);
        boolean check1 = requestId != null && checkId != null && approvedId != null && remark != null && rejectId == null;
        boolean check2 = requestId != null && checkId != null && approvedId != null && remark == null && rejectId == null;
        boolean check3 = requestId != null && checkId != null && remark != null && approvedId == null && rejectId == null;
        boolean check4 = requestId != null && checkId != null && approvedId == null && rejectId == null && remark == null;
        boolean check5 = requestId != null && remark != null && checkId == null && approvedId == null && rejectId == null;
        boolean check6 = requestId != null && remark == null && checkId == null && approvedId == null && rejectId == null;
        boolean check7 = checkId != null && remark != null && requestId == null && approvedId == null && rejectId == null;
        boolean check8 = checkId != null && remark == null && requestId == null && approvedId == null && rejectId == null;
        boolean check9 = approvedId != null && remark != null &&  requestId == null && checkId == null && rejectId == null;
        boolean check10 = approvedId != null && remark == null && requestId == null && checkId == null && rejectId == null;
        boolean check11 = rejectId != null && remark != null && requestId == null && approvedId == null && checkId == null;
        boolean check12 = rejectId != null && remark == null && requestId == null && approvedId == null && checkId == null;
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
        Page<Import> pages = null;
        List<ReportPOResponse> list = new ArrayList<>();
        long totalCount = 0;
        if (check1) {
            pages = importRepository.findByDateLocalBetweenAndCreateByAndCheckByAndApproveByAndRemark(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest,
                    requestId,
                    checkId,
                    approvedId,
                    remark
            );
        } else if (check2) {
            pages = importRepository.findByDateLocalBetweenAndCreateByAndCheckByAndApproveBy(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest,
                    requestId,
                    checkId,
                    approvedId
            );
        } else if (check3) {
            pages = importRepository.findByDateLocalBetweenAndCreateByAndCheckByAndRemark(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest,
                    requestId,
                    checkId,
                    remark);
        } else if (check4) {
            pages = importRepository.findByDateLocalBetweenAndCreateByAndCheckBy(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest,
                    requestId,
                    checkId);

        } else if (check5) {
            pages = importRepository.findByDateLocalBetweenAndCreateByAndRemark(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest,
                    requestId,
                    remark);
        } else if (check6) {
            pages = importRepository.findByDateLocalBetweenAndCreateBy(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest,
                    requestId);
        } else if (check7) {
            pages = importRepository.findByDateLocalBetweenAndCheckByAndRemark(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest,
                    checkId,
                    remark);
        } else if (check8) {
            pages = importRepository.findByDateLocalBetweenAndCheckBy(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest,
                    checkId);
        } else if (check9) {
            pages = importRepository.findByDateLocalBetweenAndApproveByAndRemark(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest,
                    approvedId,
                    remark);
        } else if (check10) {
            pages = importRepository.findByDateLocalBetweenAndApproveBy(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest,
                    approvedId);
        } else if (check11) {
            pages = importRepository.findByDateLocalBetweenAndRejectByAndRemark(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest,
                    rejectId,
                    remark);
        } else if (check12) {
            pages = importRepository.findByDateLocalBetweenAndRejectBy(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest,
                    rejectId);
        } else {
            pages = importRepository.findByDateLocalBetween(
                    LocalDate.parse(dateFrom),
                    LocalDate.parse(dateTo),
                    pageRequest);
        }
        totalCount = pages.getTotalElements();

        for (Import data : pages.getContent()) {

            if( data.getVendor().getVendorName().toLowerCase().contains(search.toLowerCase()) ) {

                String requestBy = null;
                User user = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(data.getCreateBy())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User not found with id : " + data.getCheckBy()));
                requestBy = user.getFullName();

                String checkBy = null;
                if (data.getCheckBy() != null) {
                    User user1 = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(data.getCheckBy())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "User not found with id : " + data.getCheckBy()));
                    checkBy = user1.getFullName();
                }

                String approvedBy = null;
                if (data.getApproveBy() != null) {
                    User user2 = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(data.getApproveBy())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "User not found with id id : " + data.getCheckBy()));
                    approvedBy = user2.getUsername();
                }

                String rejectBy = null;
                if (data.getRejectBy() != null) {
                    User user3 = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(data.getRejectBy())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "User not found with id id : " + data.getCheckBy()));
                    rejectBy = user3.getUsername();
                }

                ReportPOResponse d = ReportPOResponse.builder()
                        .purchaseOrderNo(data.getImpNo())
                        .transactionDate(data.getTransactionDate())
                        .transactionNo(data.getId())
                        .orderDate(data.getImpDate())
                        .referenceNo(data.getReferenceNo())
                        .vendorName(data.getVendor().getVendorName())
                        .totalQty(data.getTotalQty())
                        .totalCost(data.getTotal())
                        .remark(data.getRemark())
                        .rejectBy(rejectBy)
                        .checkBy(checkBy)
                        .approvedBy(approvedBy)
                        .rejectBy(rejectBy)
                        .requestBy(requestBy)
                        .checkDate(data.getCheckDate())
                        .approvedDate(data.getApproveDate())
                        .rejectDate(data.getRejectDate())
                        .build();
                list.add(d);
            }
        }

        // Build and return JavaCollectionResponse with results
        return JavaCollectionResponse.builder()
                .count(list.size())
                .data(list)
                .build();
    }

    private void validationDate(String dateFrom, String dateTo) {
        if( dateFrom == null ) throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"dateFrom can not be null.");
        if( dateTo == null ) throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"dateTo can not be null.");
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

    @Override
    public JavaCollectionResponse<?> getReportByRemark(Integer pageNumber, Integer pageSize, String dateFrom,
                                                       String dateTo, Integer requestId, Integer checkId, Integer approvedId, Integer rejectId, String remark) {

        validationDate(dateFrom, dateTo);

        long totalCount = 0;
        List<ReportPOResponse> list = new ArrayList<>();
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");

        boolean check1 = requestId != null && checkId != null && approvedId != null && remark != null && rejectId == null;
        boolean check2 = requestId != null && checkId != null && approvedId != null && remark == null && rejectId == null;
        boolean check3 = requestId != null && checkId != null && remark != null && approvedId == null && rejectId == null;
        boolean check4 = requestId != null && checkId != null && approvedId == null && rejectId == null && remark == null;
        boolean check5 = requestId != null && remark != null && checkId == null && approvedId == null && rejectId == null;
        boolean check6 = requestId != null && remark == null && checkId == null && approvedId == null && rejectId == null;
        boolean check7 = checkId != null && remark != null && requestId == null && approvedId == null && rejectId == null;
        boolean check8 = checkId != null && remark == null && requestId == null && approvedId == null && rejectId == null;
        boolean check9 = approvedId != null && remark != null &&  requestId == null && checkId == null && rejectId == null;
        boolean check10 = approvedId != null && remark == null && requestId == null && checkId == null && rejectId == null;
        boolean check11 = rejectId != null && remark != null && requestId == null && approvedId == null && checkId == null;
        boolean check12 = rejectId != null && remark == null && requestId == null && approvedId == null && checkId == null;


        if (pageNumber != null && pageSize != null) {
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
            Page<Import> pages = null;

            if (check1) {
                pages = importRepository.findByDateLocalBetweenAndCreateByAndCheckByAndApproveByAndRemark(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest,
                        requestId,
                        checkId,
                        approvedId,
                        remark
                );
            } else if (check2) {
                pages = importRepository.findByDateLocalBetweenAndCreateByAndCheckByAndApproveBy(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest,
                        requestId,
                        checkId,
                        approvedId
                );
            } else if (check3) {
                pages = importRepository.findByDateLocalBetweenAndCreateByAndCheckByAndRemark(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest,
                        requestId,
                        checkId,
                        remark);
            } else if (check4) {
                pages = importRepository.findByDateLocalBetweenAndCreateByAndCheckBy(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest,
                        requestId,
                        checkId);

            } else if (check5) {
                pages = importRepository.findByDateLocalBetweenAndCreateByAndRemark(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest,
                        requestId,
                        remark);
            } else if (check6) {
                pages = importRepository.findByDateLocalBetweenAndCreateBy(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest,
                        requestId);
            } else if (check7) {
                pages = importRepository.findByDateLocalBetweenAndCheckByAndRemark(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest,
                        checkId,
                        remark);
            } else if (check8) {
                pages = importRepository.findByDateLocalBetweenAndCheckBy(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest,
                        checkId);
            } else if (check9) {
                pages = importRepository.findByDateLocalBetweenAndApproveByAndRemark(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest,
                        approvedId,
                        remark);
            } else if (check10) {
                pages = importRepository.findByDateLocalBetweenAndApproveBy(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest,
                        approvedId);
            } else if (check11) {
                pages = importRepository.findByDateLocalBetweenAndRejectByAndRemark(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest,
                        rejectId,
                        remark);
            } else if (check12) {
                pages = importRepository.findByDateLocalBetweenAndRejectBy(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest,
                        rejectId);
            } else {
                pages = importRepository.findByDateLocalBetween(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        pageRequest);
            }

            totalCount = pages.getTotalElements();

            for (Import data : pages.getContent()) {
                String requestBy = null;
                User user = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(data.getCreateBy())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User not found with id : " + data.getCheckBy()));
                requestBy = user.getFullName();

                String checkBy = null;
                if (data.getCheckBy() != null) {
                    User user1 = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(data.getCheckBy())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "User not found with id : " + data.getCheckBy()));
                    checkBy = user1.getFullName();
                }

                String approvedBy = null;
                if (data.getApproveBy() != null) {
                    User user2 = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(data.getApproveBy())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "User not found with id id : " + data.getCheckBy()));
                    approvedBy = user2.getFullName();
                }

                String rejectBy = null;
                if (data.getRejectBy() != null) {
                    User user3 = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(data.getRejectBy())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "User not found with id id : " + data.getCheckBy()));
                    rejectBy = user3.getFullName();
                }

                ReportPOResponse d = ReportPOResponse.builder()
                        .purchaseOrderNo(data.getImpNo())
                        .transactionDate(data.getTransactionDate())
                        .transactionNo(data.getId())
                        .orderDate(data.getImpDate())
                        .referenceNo(data.getReferenceNo())
                        .vendorName(data.getVendor().getVendorName())
                        .totalQty(data.getTotalQty())
                        .totalCost(data.getTotal())
                        .remark(data.getRemark())
                        .rejectBy(rejectBy)
                        .checkBy(checkBy)
                        .approvedBy(approvedBy)
                        .rejectBy(rejectBy)
                        .requestBy(requestBy)
                        .checkDate(data.getCheckDate())
                        .approvedDate(data.getApproveDate())
                        .rejectDate(data.getRejectDate())
                        .build();
                list.add(d);
            }

        } else {

            List<Import> datas = null;


            if( check1 ) {
                datas = importRepository.findByDateLocalBetweenAndCreateByAndCheckByAndApproveByAndRemark(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        requestId,
                        checkId,
                        approvedId,
                        remark
                );
            } else if ( check2 ) {
                datas = importRepository.findByDateLocalBetweenAndCreateByAndCheckByAndApproveBy(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        requestId,
                        checkId,
                        approvedId
                );
            } else if ( check3 ) {
                datas = importRepository.findByDateLocalBetweenAndCreateByAndCheckByAndRemark(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        requestId,
                        checkId,
                        remark);
            } else if ( check4 ) {
                datas = importRepository.findByDateLocalBetweenAndCreateByAndCheckBy(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        requestId,
                        checkId);
            } else if ( check5 ) {
                datas = importRepository.findByDateLocalBetweenAndCreateByAndRemark(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        requestId,
                        remark);
            } else if ( check6 ) {
                datas = importRepository.findByDateLocalBetweenAndCreateBy(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        requestId);
            } else if ( check7 ) {
                datas = importRepository.findByDateLocalBetweenAndCheckByAndRemark(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        checkId,
                        remark);
            } else if ( check8 ) {
                datas = importRepository.findByDateLocalBetweenAndCheckBy(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        checkId);
            } else if ( check9 ) {
                datas = importRepository.findByDateLocalBetweenAndApproveByAndRemark(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        approvedId,
                        remark);
            } else if ( check10 ) {
                datas = importRepository.findByDateLocalBetweenAndApproveBy(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        approvedId);
            } else if ( check11 ) {
                datas = importRepository.findByDateLocalBetweenAndRejectByAndRemark(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        rejectId,
                        remark);
            } else  if ( check12 ) {
                datas = importRepository.findByDateLocalBetweenAndRejectBy(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo),
                        rejectId);
            } else {
                datas = importRepository.findByDateLocalBetween(
                        LocalDate.parse(dateFrom),
                        LocalDate.parse(dateTo));
            }

            totalCount = datas.size();
            for (Import data : datas) {
                String requestBy = null;
                User user = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(data.getCreateBy())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User not found with id : " + data.getCheckBy()));
                requestBy = user.getFullName();

                String checkBy = null;
                if (data.getCheckBy() != null) {
                    User user1 = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(data.getCheckBy())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "User not found with id : " + data.getCheckBy()));
                    checkBy = user1.getFullName();
                }

                String approvedBy = null;
                if (data.getApproveBy() != null) {
                    User user2 = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(data.getApproveBy())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "User not found with id id : " + data.getCheckBy()));
                    approvedBy = user2.getUsername();
                }

                String rejectBy = null;
                if (data.getRejectBy() != null) {
                    User user3 = userRepository.findByIdAndStatusTrueAndIsDeletedFalse(data.getRejectBy())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "User not found with id id : " + data.getCheckBy()));
                    rejectBy = user3.getUsername();
                }

                ReportPOResponse d = ReportPOResponse.builder()
                        .purchaseOrderNo(data.getImpNo())
                        .transactionDate(data.getTransactionDate())
                        .transactionNo(data.getId())
                        .orderDate(data.getImpDate())
                        .referenceNo(data.getReferenceNo())
                        .vendorName(data.getVendor().getVendorName())
                        .totalQty(data.getTotalQty())
                        .totalCost(data.getTotal())
                        .remark(data.getRemark())
                        .rejectBy(rejectBy)
                        .checkBy(checkBy)
                        .approvedBy(approvedBy)
                        .rejectBy(rejectBy)
                        .requestBy(requestBy)
                        .checkDate(data.getCheckDate())
                        .approvedDate(data.getApproveDate())
                        .rejectDate(data.getRejectDate())
                        .build();
                list.add(d);
            }
        }

        // Build and return JavaCollectionResponse with results
        return JavaCollectionResponse.builder()
                .count(totalCount)
                .data(list)
                .build();
    }

    /**
     * Filters imports based on vendor name and returns a paginated response.
     *
     * @param pageNumber Page number for pagination.
     * @param pageSize   Size of each page.
     * @param vendorName Name of the vendor to filter by (case-insensitive).
     * @return JavaCollectionResponse containing filtered data and count.
     */
    @Override
    public JavaCollectionResponse<?> filter(int pageNumber, int pageSize, String vendorName) {
        // Define sorting criteria by id in descending order
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");

        // Create page request for the specified page number, page size, and sorting
        // criteria
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        // Fetch a page of Import entities that are active and not deleted
        Page<Import> pages = importRepository.findByStatusTrueAndIsDeletedFalse(pageRequest);

        // Filter the content based on vendorName and map to ReportPurchaseOrderResponse
        // DTOs
        List<ReportPurchaseOrderResponse> data = pages.getContent().stream()
                .filter(p -> {
                    // Filter condition: vendorName must not be null and must match ignoring case
                    return vendorName != null &&
                            p.getVendor() != null &&
                            p.getVendor().getVendorName() != null &&
                            p.getVendor().getVendorName().toLowerCase()
                                    .contains(vendorName.toLowerCase());
                })
                .map(importMapper::mapToReportPurchaseOrder)
                .toList();

        // Build and return a JavaCollectionResponse with the count and filtered data
        return JavaCollectionResponse.builder()
                .count(data.size())
                .data(data)
                .build();
    }

    /**
     * Generates a report of purchase orders within a specified date range.
     *
     *
     * @return JavaCollectionResponse containing report data and count.
     */
    @Override
    public JavaCollectionResponse<?> reportPurchaseOrder(Integer pageNumber, Integer pageSize, String dateFrom,
                                                         String dateTo) {
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
        List<ReportPurchaseOrderResponse> reportPurchaseOrderResponses = new ArrayList<>();
        long totalCount = 0;
        if (pageNumber != null && pageSize != null) {
            Sort sortById = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);
            // Fetch imports between dateFromLocal and dateToLocal
            Page<Import> pages = importRepository.findByDateLocalBetween(dateFromLocal, dateToLocal, pageRequest);
            totalCount = pages.getTotalElements();

            // Map Import entities to ReportPurchaseOrderResponse DTOs
            reportPurchaseOrderResponses = pages.getContent().stream()
                    .map(this::mapToReportPurchaseOrderResponse)
                    .toList();
        } else {
            // Fetching data without pagination
            List<Import> imports = importRepository.findByDateLocalBetween(dateFromLocal, dateToLocal);

            // Mapping Import entities to ReportPurchaseOrderResponse DTOs
            reportPurchaseOrderResponses = imports.stream()
                    .map(this::mapToReportPurchaseOrderResponse) // Assuming mapToReportPurchaseOrderResponse is
                    // a// method reference
                    .toList(); // Collecting results into a List

            totalCount = reportPurchaseOrderResponses.size();
        }

        // Build and return JavaCollectionResponse with results
        return JavaCollectionResponse.builder()
                .count(totalCount)
                .data(reportPurchaseOrderResponses)
                .build();
    }

    private ReportPurchaseOrderResponse mapToReportPurchaseOrderResponse(Import imp) {
        return ReportPurchaseOrderResponse.builder()
                .purchaseOrderNo("PO-" + imp.getImpNo())
                .transactionNo(imp.getId())
                .transactionDate(imp.getTransactionDate())
                .orderDate(imp.getImpDate())
                .referenceNo(imp.getReferenceNo())
                .vendorName(imp.getVendor().getVendorName())
                .totalQty(imp.getTotalQty())
                .totalCost(imp.getTotal())
                .build();
    }
}
