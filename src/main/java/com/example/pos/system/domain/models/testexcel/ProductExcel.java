package com.example.pos.system.domain.models.testexcel;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductExcel {
     private String productName;
     private String barcode;
     private BigDecimal price;
     private String qty;
     private String status;
}
