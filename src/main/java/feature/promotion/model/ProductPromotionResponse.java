package feature.promotion.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class ProductPromotionResponse {

     private int count;
     private ProductPromotionResponseDetail[] data;

     @Setter
     @Getter
     @NoArgsConstructor
     public static class ProductPromotionResponseDetail {

          private int productId;
          private String barcode;
          private String categoryName;
          private String englishDescription;
          private int onHandQty;
          private BigDecimal salePrice;
          private String khrDescription;
          private String division;
          private String department;
          private String percentage;
          private BigDecimal afterDiscount;

     }
}
