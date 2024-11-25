package com.example.pos.system.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProIdModel {

     private int proId;
     private int qty;
     private String sign;
 

}
