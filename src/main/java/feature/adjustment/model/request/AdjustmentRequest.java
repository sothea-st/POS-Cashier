 
package feature.adjustment.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AdjustmentRequest {
     private Integer productId;
     private Integer qty;
}
