package com.example.pos.system.layer.projections.holdProjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HoldDataModel {
     private int id;
     private String flag;
     private double cost;
     private int catID;
     private int qty;
     private String barcode;
     private String weight;
     private double price;
     private String proNameKh;
     private int brandID;
     private String productStatus;
     private String codeExpired;
     private String codeOutStock;
     private String proNameEn;
     private String proImageName;
     private int discount;
}
