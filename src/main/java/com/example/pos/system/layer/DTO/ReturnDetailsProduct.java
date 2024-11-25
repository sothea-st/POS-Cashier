package com.example.pos.system.layer.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnDetailsProduct {
 
     private int qty;
     private BigDecimal price;
     private String pro_name_en;
     private String barcode;
     
}
