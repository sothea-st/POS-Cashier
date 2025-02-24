 
package feature.promotion.model;
 
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PromotionDetailRequest {
     private Integer productId;
     private Integer percentage;
     private BigDecimal afterDiscount;
}
