 
package feature.adjustment.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductAdjustment {
     
     private Integer id;
     private String itemCode;
     private String barcode;
     private String proNameEn;
     private String proNameKh;
     private String uom;
     private Integer onHandQty;
     private Integer adjustQty;
     private BigDecimal cost;
     
}
