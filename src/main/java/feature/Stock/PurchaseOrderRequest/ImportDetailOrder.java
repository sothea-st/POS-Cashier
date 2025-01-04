/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feature.Stock.PurchaseOrderRequest;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOBILE-APP.02
 */
@Getter
@Setter
public class ImportDetailOrder {

     private Integer ID;
     private String barcode;
     private String proNameEn;
     private String division;
     private Integer availableQty;
     private Integer qty;
     private BigDecimal cost;
     private BigDecimal amount;

     public ImportDetailOrder(){
     
     }
     
     public ImportDetailOrder(
          Integer id,
          String barcode,
          String proNameEn,
          String division,
          Integer availableQty,
          Integer qty,
          BigDecimal cost,
          BigDecimal amount
     ) {
          this.ID = id;
          this.barcode = barcode;
          this.proNameEn = proNameEn;
          this.division = division;
          this.availableQty = availableQty;
          this.qty = qty;
          this.cost = cost;
          this.amount = amount;
     }
}
