package feature.promotion.model;

import java.math.BigDecimal;
import lombok.Getter;
@Getter
public class PromotionModel {

     private int count;
     private PromotionDetail[] data;

     @Getter
     public static class PromotionDetail {
          private int id;
          private String createdDate;
          private String createdBy;
          private String promotionType;
          private String startDate;
          private String endDate;
          private String percentage;
          private BigDecimal salePrice;
          private BigDecimal afterDiscount;
          private Boolean isStatus;
     }
}
