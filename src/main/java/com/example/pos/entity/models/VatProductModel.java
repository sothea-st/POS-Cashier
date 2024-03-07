package com.example.pos.entity.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VatProductModel {
     private String title;
     private BigDecimal total; 
}
