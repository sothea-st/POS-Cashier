package com.example.pos.connection1.DTO;

import java.math.BigDecimal;

import com.example.pos.connection1.entity.projection.SaleDetailProjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnDetailsProduct {
 
     private int qty;
     private BigDecimal price;
     private String proName;
     private String barcode;
     
}
