package com.example.pos.connection1.feature.product.productExcel.dto;

import com.example.pos.connection1.feature.product.dto.ProductMultipleRequest;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class ProductMultipleInsert {
     private List<ProductExcelDetail> lists;
}
