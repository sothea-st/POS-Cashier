
package Model.Report;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ReportSaleDetail {
     private String saleDate;
     private String proNameEn;
     private String proImageName;
     private int qty;
     private String discountCase;
     private BigDecimal discountPercentage;
     private BigDecimal discount;
     private BigDecimal price;
     private BigDecimal amountWithTax;
     private String taxType;
     private BigDecimal totalSaledExcludeVAT;
     private BigDecimal vatAmt;
     private BigDecimal plt;
     private BigDecimal netSale;
     private BigDecimal cost;
     private BigDecimal margin;
     private String userName;
     private String barcode;
     private String invoiceNumber;
     private String transactionType;
     private String posId;
     private String paymentStatus;
     private String paymentMethod;
     private BigDecimal totalOrder;
     private String orderSource;
     private String customerType;
     private BigDecimal khqrCode;
}
