package feature.report.report_stock.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import pagination.PaginationData;

@Setter
@Getter
public class StockAvailableResponse implements PaginationData {

     private int count;
     private List<StockAvailableDetail> data;

     @Setter
     @Getter
     public static class StockAvailableDetail {
          private String productName;
          private String productImage;
          private String categoryName;
          private String supplierName;
          private BigDecimal price;
          private BigDecimal cost;
          private int qty;
          private String date;
          private String description;
     }
}
