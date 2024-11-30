package Reporting.ReportInventory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReportInventoryModel {

     private int count;
     private ReportInventoryDetail[] data;

     @Setter
     @Getter
     public static class ReportInventoryDetail {

          private long id;
          private String date;
          private String productName;
          private int beginningQty;
          private int stockInQty;
          private int availableQty;
          private int returnOutQty;
          private int returnInQty;
          private int stockOutQty;
          private int endingQty;
     }

}
