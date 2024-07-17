package Reporting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.*;
public class ReportingDetailResponse {
     private String purchaseOrderNo;
     private int transactionNo;
     private String transactionDate;
     private String orderDate;
     private String referenceNo;
     private String vendorName;
     private int totalQty;
     private BigDecimal totalCost;

     @JsonProperty("purchaseOrderNo")
     public String getPurchaseOrderNo() {
          return purchaseOrderNo;
     }

     @JsonProperty("purchaseOrderNo")
     public void setPurchaseOrderNo(String value) {
          this.purchaseOrderNo = value;
     }

     @JsonProperty("transactionNo")
     public int getTransactionNo() {
          return transactionNo;
     }

     @JsonProperty("transactionNo")
     public void setTransactionNo(int value) {
          this.transactionNo = value;
     }

     @JsonProperty("transactionDate")
     public String getTransactionDate() {
          return transactionDate;
     }

     @JsonProperty("transactionDate")
     public void setTransactionDate(String value) {
          this.transactionDate = value;
     }

     @JsonProperty("orderDate")
     public String getOrderDate() {
          return orderDate;
     }

     @JsonProperty("orderDate")
     public void setOrderDate(String value) {
          this.orderDate = value;
     }

     @JsonProperty("referenceNo")
     public String getReferenceNo() {
          return referenceNo;
     }

     @JsonProperty("referenceNo")
     public void setReferenceNo(String value) {
          this.referenceNo = value;
     }

     @JsonProperty("vendorName")
     public String getVendorName() {
          return vendorName;
     }

     @JsonProperty("vendorName")
     public void setVendorName(String value) {
          this.vendorName = value;
     }

     @JsonProperty("totalQty")
     public int getTotalQty() {
          return totalQty;
     }

     @JsonProperty("totalQty")
     public void setTotalQty(int value) {
          this.totalQty = value;
     }

     @JsonProperty("totalCost")
     public BigDecimal getTotalCost() {
          return totalCost;
     }

     @JsonProperty("totalCost")
     public void setTotalCost(BigDecimal value) {
          this.totalCost = value;
     }
}
