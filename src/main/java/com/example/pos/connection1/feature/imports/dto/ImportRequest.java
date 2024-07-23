package com.example.pos.connection1.feature.imports.dto;

import com.example.pos.connection1.constant.JavaMessage;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.*;
public record ImportRequest(
     @NotNull(message = JavaMessage.required)
     Integer createBy,

     @NotNull(message = JavaMessage.required)
     Integer empId,

     @NotNull(message = JavaMessage.required)
     Integer vendorId,

     @NotBlank(message = JavaMessage.required)
     String impDate,
 
     String transactionDate,
     String referenceNo,

     BigDecimal discount,

     @NotNull(message = JavaMessage.required)
     BigDecimal total,

     @NotNull(message = JavaMessage.required)
     Integer totalQty,

     @NotBlank(message = JavaMessage.required)
     String remark,

     @NotEmpty(message = JavaMessage.required)
     List<ImportDetailsRequest> details,

     Integer impId

) {
     
}
