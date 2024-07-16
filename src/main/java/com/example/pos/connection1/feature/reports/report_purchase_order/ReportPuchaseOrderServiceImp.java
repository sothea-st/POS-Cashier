package com.example.pos.connection1.feature.reports.report_purchase_order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.pos.connection1.entity.Import;
import com.example.pos.connection1.feature.imports.ImportRepository;
import com.example.pos.connection1.feature.reports.report_purchase_order.dto.ReportPurchaseOrderRequest;
import com.example.pos.connection1.feature.reports.report_purchase_order.dto.ReportPurchaseOrderResponse;
import com.example.pos.connection1.mapper.ImportMapper;
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
      * @param reportPurchaseOrderRequest Request containing date range for the
      *                                   report.
      * @return JavaCollectionResponse containing report data and count.
      */
     @Override
     public JavaCollectionResponse<?> reportPurchaseOrder(ReportPurchaseOrderRequest reportPurchaseOrderRequest) {
          LocalDate dateFromLocal;
          LocalDate dateToLocal;

          try {
               // Parse dateFrom and dateTo from the request
               dateFromLocal = LocalDate.parse(reportPurchaseOrderRequest.dateFrom());
               dateToLocal = LocalDate.parse(reportPurchaseOrderRequest.dateTo());

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

          // Fetch imports between dateFromLocal and dateToLocal
          List<Import> imports = importRepository.findByDateLocalBetween(dateFromLocal, dateToLocal);

          // Map Import entities to ReportPurchaseOrderResponse DTOs
          List<ReportPurchaseOrderResponse> reportPurchaseOrderResponses = imports.stream()
                    .map(imp -> ReportPurchaseOrderResponse.builder()
                              .purchaseOrderNo("PO-" + imp.getImpNo())
                              .transactionNo(imp.getId())
                              .transactionDate(imp.getTransactionDate())
                              .orderDate(imp.getImpDate())
                              .referenceNo(imp.getReferenceNo())
                              .vendorName(imp.getVendor().getVendorName())
                              .totalQty(imp.getTotalQty())
                              .totalCost(imp.getTotal())
                              .build())
                    .toList();

          // Build and return JavaCollectionResponse with results
          return JavaCollectionResponse.builder()
                    .count(reportPurchaseOrderResponses.size())
                    .data(reportPurchaseOrderResponses)
                    .build();
     }

}
