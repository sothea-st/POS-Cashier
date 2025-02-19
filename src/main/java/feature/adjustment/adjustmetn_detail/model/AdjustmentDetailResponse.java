package feature.adjustment.adjustmetn_detail.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdjustmentDetailResponse {

     private int status;
     private String msg;
     private AdjustmentData data;
     

     @Setter
     @Getter
     public static class AdjustmentData {
          private Reason reason;
          private String reference;
          private String transactionDate;
          private String comment;
          private int totalQty;
          private BigDecimal totalCost;
          private String transaction;
          private Detail[] details;
     }

     @Setter
     @Getter
     public static class Detail {
          private Integer productId;
          private String itemCode;
          private String barcode;
          private String productNameEn;
          private String productNameKh;
          private String oum;
          private int onHandQty;
          private int adjustQty;
          private BigDecimal cost;
     }

     @Setter
     @Getter
     public static class Reason {
          private int id;
          private String name;
     }

}
