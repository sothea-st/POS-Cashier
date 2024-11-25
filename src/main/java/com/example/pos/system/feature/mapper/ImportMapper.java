package com.example.pos.system.feature.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.pos.system.domain.Import;
import com.example.pos.system.feature.imports.dto.ImportResponse;
import com.example.pos.system.feature.reports.report_purchase_order.dto.ReportPurchaseOrderResponse;

@Mapper(componentModel = "spring")
public interface ImportMapper {

     /**
      * Maps an Import entity to an ImportResponse DTO.
      * 
      * @param imports The Import entity to map from.
      * @return An ImportResponse DTO mapped from the Import entity.
      */
     @Mapping(source = "total", target = "totalCost") // Maps 'total' field in Import to 'totalCost' in ImportResponse
     @Mapping(source = "impNo", target = "transactionNo") // Maps 'impNo' field in Import to 'transactionNo' in ImportResponse
     @Mapping(source = "imports.vendor.vendorName", target = "vendorName") // Maps 'vendorName' from nested 'vendor' in Import to 'vendorName' in ImportResponse
     ImportResponse mapToImportResponse(Import imports);


     @Mapping(source = "impNo" , target = "purchaseOrderNo")
     @Mapping(source = "id" , target = "transactionNo" )
     @Mapping(source = "impDate" , target = "orderDate")
     @Mapping(source = "imports.vendor.vendorName" , target = "vendorName")
     @Mapping(source = "total" , target = "totalCost")
     ReportPurchaseOrderResponse mapToReportPurchaseOrder(Import imports);
}
