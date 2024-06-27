package com.example.pos.connection1.entity.models.testexcel;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestFileImportExcel {
     private String itemName;
     private String unit;
     private double qty;
     private BigDecimal rate;
     private BigDecimal value;
}
