package com.example.pos.system.layer.projections.holdProjection;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HoldDetailModel {
     private int id;
     private String note;
     private int qtyHold;
     private List<HoldProjection> listDetails; 
}
