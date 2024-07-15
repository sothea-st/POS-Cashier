package com.example.pos.connection1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.pos.connection1.entity.Import;
import com.example.pos.connection1.feature.imports.dto.ImportResponse;

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
}
