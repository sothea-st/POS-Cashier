package feature.report.report_product_stock_in.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import pagination.PaginationData;

@Setter
@Getter
public class ReportProductStockInResponse implements PaginationData {

     private int count;
     private List<ReportProductStockInDetailResponse> data;

     @Setter
     @Getter
     public static class ReportProductStockInDetailResponse {
          private String productName;
          private String productImage;
          private String categoryName;
          private String supplierName;
          private BigDecimal price;
          private Object cost;
          private int qty;
          private String date;
          private String description;
     }

}
