package com.example.pos.connection2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModelD2 {
     private String name;
     private String nameKh;
     private String image;
     private double price =0;
     private double cost = 0;
     private String status;
     private String barcode;
     private double discount=0;
     private String choiceOptions;
}
