package feature.report.report_sale_return.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReportSaleReturnResponse {

     private int count;
     private ReportSaleReturnDetailResponse[] data;

     @Setter
     @Getter
     public static class ReportSaleReturnDetailResponse {

          private String date;
          private BigDecimal cost;
          private BigDecimal price;
          private int qty;
          private BigDecimal discount;
          private String staff;
          @JsonProperty("product_name")
          private String productName;
          @JsonProperty("invoice_no")
          private String invoiceNo;
          private String reason;
          private String choices;

     }
}
