package Stock.PurchaseOrderCheck;

import java.math.BigDecimal;

import org.apache.xmlbeans.impl.soap.Detail;

import com.fasterxml.jackson.annotation.JsonProperty;

public class POCheckDetailsModel {
     private Integer transactionNo;
     private String purchaseOrderNo;
     private String transactionDate;
     private Integer vendorID;
     private String vendorName;
     private Integer totalQty;
     private BigDecimal totalCost;
     private String referenceNo;
     private String orderDate;
     private DetailsByModel requestBy;
     private DetailsByModel checkedBy;
     private DetailsByModel approvedBy;
     private String rejectBy;
     private String feedBackReject;
     private String remark;
     private PODetailItemModel[] details;

     @JsonProperty("transactionNo")
     public Integer getTransactionNo() {
          return transactionNo;
     }

     @JsonProperty("transactionNo")
     public void setTransactionNo(Integer value) {
          this.transactionNo = value;
     }

     @JsonProperty("purchaseOrderNo")
     public String getPurchaseOrderNo() {
          return purchaseOrderNo;
     }

     @JsonProperty("purchaseOrderNo")
     public void setPurchaseOrderNo(String value) {
          this.purchaseOrderNo = value;
     }

     @JsonProperty("transactionDate")
     public String getTransactionDate() {
          return transactionDate;
     }

     @JsonProperty("transactionDate")
     public void setTransactionDate(String value) {
          this.transactionDate = value;
     }

     @JsonProperty("vendorId")
     public Integer getVendorID() {
          return vendorID;
     }

     @JsonProperty("vendorId")
     public void setVendorID(Integer value) {
          this.vendorID = value;
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
     public Integer getTotalQty() {
          return totalQty;
     }

     @JsonProperty("totalQty")
     public void setTotalQty(Integer value) {
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

     @JsonProperty("referenceNo")
     public String getReferenceNo() {
          return referenceNo;
     }

     @JsonProperty("referenceNo")
     public void setReferenceNo(String value) {
          this.referenceNo = value;
     }

     @JsonProperty("orderDate")
     public String getOrderDate() {
          return orderDate;
     }

     @JsonProperty("orderDate")
     public void setOrderDate(String value) {
          this.orderDate = value;
     }

     @JsonProperty("requestBy")
     public DetailsByModel getRequestBy() {
          return requestBy;
     }

     @JsonProperty("requestBy")
     public void setRequestBy(DetailsByModel value) {
          this.requestBy = value;
     }

     @JsonProperty("checkedBy")
     public DetailsByModel getCheckedBy() {
          return checkedBy;
     }

     @JsonProperty("checkedBy")
     public void setCheckedBy(DetailsByModel value) {
          this.checkedBy = value;
     }

     @JsonProperty("approvedBy")
     public DetailsByModel getApprovedBy() {
          return approvedBy;
     }

     @JsonProperty("approvedBy")
     public void setApprovedBy(DetailsByModel value) {
          this.approvedBy = value;
     }

     @JsonProperty("rejectBy")
     public String getRejectBy() {
          return rejectBy;
     }

     @JsonProperty("rejectBy")
     public void setRejectBy(String value) {
          this.rejectBy = value;
     }

     @JsonProperty("feedBackReject")
     public String getFeedBackReject() {
          return feedBackReject;
     }

     @JsonProperty("feedBackReject")
     public void setFeedBackReject(String value) {
          this.feedBackReject = value;
     }

     @JsonProperty("remark")
     public String getRemark() {
          return remark;
     }

     @JsonProperty("remark")
     public void setRemark(String value) {
          this.remark = value;
     }

     @JsonProperty("details")
     public PODetailItemModel[] getDetails() {
          return details;
     }

     @JsonProperty("details")
     public void setDetails(PODetailItemModel[] value) {
          this.details = value;
     }
}
