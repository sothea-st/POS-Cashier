package feature.Stock.PurchaseOrderRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.*;

public class ImportRequest {

     private Integer createBy;
     private Integer empID;
     private Integer subID;
     private String impDate;
     private BigDecimal discount;
     private Integer total;
     private ImportDetailRequest[] details;

     @JsonProperty("createBy")
     public Integer getCreateBy() {
          return createBy;
     }

     @JsonProperty("createBy")
     public void setCreateBy(Integer value) {
          this.createBy = value;
     }

     @JsonProperty("emp_id")
     public Integer getEmpID() {
          return empID;
     }

     @JsonProperty("emp_id")
     public void setEmpID(Integer value) {
          this.empID = value;
     }

     @JsonProperty("sub_id")
     public Integer getSubID() {
          return subID;
     }

     @JsonProperty("sub_id")
     public void setSubID(Integer value) {
          this.subID = value;
     }

     @JsonProperty("impDate")
     public String getImpDate() {
          return impDate;
     }

     @JsonProperty("impDate")
     public void setImpDate(String value) {
          this.impDate = value;
     }

     @JsonProperty("discount")
     public BigDecimal getDiscount() {
          return discount;
     }

     @JsonProperty("discount")
     public void setDiscount(BigDecimal value) {
          this.discount = value;
     }

     @JsonProperty("total")
     public Integer getTotal() {
          return total;
     }

     @JsonProperty("total")
     public void setTotal(Integer value) {
          this.total = value;
     }

     @JsonProperty("details")
     public ImportDetailRequest[] getDetails() {
          return details;
     }

     @JsonProperty("details")
     public void setDetails(ImportDetailRequest[] value) {
          this.details = value;
     }

     private Integer productId;
     private Integer qtyNew;
     private double cost;
     private Integer amount;
     private String expireDate;

     @JsonProperty("productId")
     public Integer getProductID() {
          return productId;
     }

     @JsonProperty("productId")
     public void setProductID(Integer value) {
          this.productId = value;
     }

     @JsonProperty("qtyNew")
     public Integer getQtyNew() {
          return qtyNew;
     }

     @JsonProperty("qtyNew")
     public void setQtyNew(Integer value) {
          this.qtyNew = value;
     }

     @JsonProperty("cost")
     public double getCost() {
          return cost;
     }

     @JsonProperty("cost")
     public void setCost(double value) {
          this.cost = value;
     }

     @JsonProperty("amount")
     public Integer getAmount() {
          return amount;
     }

     @JsonProperty("amount")
     public void setAmount(Integer value) {
          this.amount = value;
     }

     @JsonProperty("expireDate")
     public String getExpireDate() {
          return expireDate;
     }

     @JsonProperty("expireDate")
     public void setExpireDate(String value) {
          this.expireDate = value;
     }

     public class ImportDetailRequests {

          private Integer productId;
          private Integer qtyNew;
          private BigDecimal cost;
          private BigDecimal amount;
          private String expireDate;
          private Integer receivedQty;

          public ImportDetailRequests(
               Integer productId,
               Integer qtyNew,
               BigDecimal cost,
               BigDecimal amount,
               String expireDate,
               Integer receivedQty
          ) {
               this.productId = productId;
               this.qtyNew = qtyNew;
               this.cost = cost;
               this.amount = amount;
               this.expireDate = expireDate;
               this.receivedQty = receivedQty;
          }

          @JsonProperty("receivedQty")
          public Integer getReceivedQty() {
               return receivedQty;
          }

          @JsonProperty("receivedQty")
          public void setReceivedQty(Integer receivedQty) {
               this.receivedQty = receivedQty;
          }

          @JsonProperty("productId")
          public Integer getProductId() {
               return productId;
          }

          @JsonProperty("productId")
          public void setProductId(Integer value) {
               this.productId = value;
          }

          @JsonProperty("qtyNew")
          public Integer getQtyNew() {
               return qtyNew;
          }

          @JsonProperty("qtyNew")
          public void setQtyNew(Integer value) {
               this.qtyNew = value;
          }

          @JsonProperty("cost")
          public BigDecimal getCost() {
               return cost;
          }

          @JsonProperty("cost")
          public void setCost(BigDecimal value) {
               this.cost = value;
          }

          @JsonProperty("amount")
          public BigDecimal getAmount() {
               return amount;
          }

          @JsonProperty("amount")
          public void setAmount(BigDecimal value) {
               this.amount = value;
          }

          @JsonProperty("expireDate")
          public String getExpireDate() {
               return expireDate;
          }

          @JsonProperty("expireDate")
          public void setExpireDate(String value) {
               this.expireDate = value;
          }
     }

     public class ImportDetailRequest {

          private Integer productId;
          private Integer qtyNew;
          private BigDecimal cost;
          private BigDecimal amount;
          private String expireDate;

          public ImportDetailRequest(
               Integer productId,
               Integer qtyNew,
               BigDecimal cost,
               BigDecimal amount,
               String expireDate
          ) {
               this.productId = productId;
               this.qtyNew = qtyNew;
               this.cost = cost;
               this.amount = amount;
               this.expireDate = expireDate;
          }

          @JsonProperty("productId")
          public Integer getProductId() {
               return productId;
          }

          @JsonProperty("productId")
          public void setProductId(Integer value) {
               this.productId = value;
          }

          @JsonProperty("qtyNew")
          public Integer getQtyNew() {
               return qtyNew;
          }

          @JsonProperty("qtyNew")
          public void setQtyNew(Integer value) {
               this.qtyNew = value;
          }

          @JsonProperty("cost")
          public BigDecimal getCost() {
               return cost;
          }

          @JsonProperty("cost")
          public void setCost(BigDecimal value) {
               this.cost = value;
          }

          @JsonProperty("amount")
          public BigDecimal getAmount() {
               return amount;
          }

          @JsonProperty("amount")
          public void setAmount(BigDecimal value) {
               this.amount = value;
          }

          @JsonProperty("expireDate")
          public String getExpireDate() {
               return expireDate;
          }

          @JsonProperty("expireDate")
          public void setExpireDate(String value) {
               this.expireDate = value;
          }
     }

}
