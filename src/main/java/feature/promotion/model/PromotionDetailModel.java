package feature.promotion.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PromotionDetailModel {

     private int status;
     private String msg;
     private PromotionDetailData data;

     @Setter
     @Getter
     public static class PromotionDetailData {
          private Integer promotionId;
          private String promotionType;
          private String startDate;
          private String endDate;
          private int percentage;
          private PromotionDetail[] details;
     }

     @Setter
     @Getter
     public static class PromotionDetail {
          private Integer productId;
          private String barcode;
          private String category;
          private String descEng;
          private String descKhr;
          private String division;
          private String department;
          private String percentage;
          private BigDecimal salePrice;
          private BigDecimal afterDiscount;
     }

}
