package Stock.PurchaseOrderRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Builder;


public class PurchaseOrderDetailResponse {

     private Integer id;
     private String barcode;
     private String proNameEn;
     private String division;
     private Integer availableQty;
     private Integer qty;
     private BigDecimal cost;
     private BigDecimal amount;
 
     
     
     @JsonProperty("id")
     public Integer getID() {
          return id;
     }

     @JsonProperty("id")
     public void setID(Integer value) {
          this.id = value;
     }

     @JsonProperty("barcode")
     public String getBarcode() {
          return barcode;
     }

     @JsonProperty("barcode")
     public void setBarcode(String value) {
          this.barcode = value;
     }

     @JsonProperty("proNameEn")
     public String getProNameEn() {
          return proNameEn;
     }

     @JsonProperty("proNameEn")
     public void setProNameEn(String value) {
          this.proNameEn = value;
     }

     @JsonProperty("division")
     public String getDivision() {
          return division;
     }

     @JsonProperty("division")
     public void setDivision(String value) {
          this.division = value;
     }

     @JsonProperty("availableQty")
     public Integer getAvailableQty() {
          return availableQty;
     }

     @JsonProperty("availableQty")
     public void setAvailableQty(Integer value) {
          this.availableQty = value;
     }

     @JsonProperty("qty")
     public Integer getQty() {
          return qty;
     }

     @JsonProperty("qty")
     public void setQty(Integer value) {
          this.qty = value;
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
}
