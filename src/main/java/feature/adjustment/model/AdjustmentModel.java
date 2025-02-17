package feature.adjustment.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdjustmentModel {

     private int count;
     private AdjustmentDetail[] data;

     @Setter
     @Getter
     public static class AdjustmentDetail {

          private int id;
          private String transaction;
          private String transactionDate;
          private String postDate;
          private String referenceName;
          private String approvalUser;
          private String reason;
          private int totalQty;
          private BigDecimal totalCost;
          private String status;
     }

}
