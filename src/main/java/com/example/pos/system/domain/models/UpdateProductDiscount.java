package com.example.pos.system.domain.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UpdateProductDiscount {
     private int id;
     private BigDecimal discount;
     public UpdateProductDiscount(){}
}
