package feature.adjustment.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductBarcode {

     private int count;
     private ProductBarcodeDetail[] data;

     @Setter
     @Getter
     public static class ProductBarcodeDetail {

          private int id;
          private String subCatNameEn;
          private String proNameKh;
          private String proNameEn;
          private BigDecimal cost;
          private BigDecimal price;
          private String margin;
          private String brandNameEn;
          private String barcode;
          private int createBy;
          private String taxName;
          private String vendorName;
          private String uomNameEn;
          private String attrNameEn;
          private String statusName;
          private String countryImageName;
          private String choices;
          private String proImageName;
          private int qty;
          private String itemCode;
          private String vendorCode;
          private String warehouse;
          private String range;
          private String slot;
     }
}
