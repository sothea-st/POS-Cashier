 
package Model.PackageProduct;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductResponse {

     private String barcode;
     private Integer vendorId;
     private Integer brandId;
     private Integer subCatId;
     private String productName;
     private String productNameKh;
     private BigDecimal cost;
     private BigDecimal price;
     private String margin;
     private Integer attributeId;
     private String choiceValue;
     private Integer uomId;
     private Integer statusId;
     private Integer countryId;
     private Integer taxId;
     private String link;
     private String photo;
     private String number;
     private Integer createBy;

     private Integer warehouseId;
     private Integer rangeId;
     private Integer slotId;

}
