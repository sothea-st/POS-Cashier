package Stock.PurchaseOrderCheck;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PODetailItemModel {
     private Integer id;
     private Integer productID;
     private String barcode;
     private String proNameEn;
     private String proNameKh;
     private String division;
     private String department;
     private String category;
     private String subCategory;
     private Integer subCategoryID;
     private Integer availableQty;
     private Integer orderQty;
     private BigDecimal cost;
     private BigDecimal totalCost;

     @JsonProperty("id")
     public Integer getID() {
          return id;
     }

     @JsonProperty("id")
     public void setID(Integer value) {
          this.id = value;
     }

     @JsonProperty("productId")
     public Integer getProductID() {
          return productID;
     }

     @JsonProperty("productId")
     public void setProductID(Integer value) {
          this.productID = value;
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

     @JsonProperty("proNameKh")
     public String getProNameKh() {
          return proNameKh;
     }

     @JsonProperty("proNameKh")
     public void setProNameKh(String value) {
          this.proNameKh = value;
     }

     @JsonProperty("division")
     public String getDivision() {
          return division;
     }

     @JsonProperty("division")
     public void setDivision(String value) {
          this.division = value;
     }

     @JsonProperty("department")
     public String getDepartment() {
          return department;
     }

     @JsonProperty("department")
     public void setDepartment(String value) {
          this.department = value;
     }

     @JsonProperty("category")
     public String getCategory() {
          return category;
     }

     @JsonProperty("category")
     public void setCategory(String value) {
          this.category = value;
     }

     @JsonProperty("subCategory")
     public String getSubCategory() {
          return subCategory;
     }

     @JsonProperty("subCategory")
     public void setSubCategory(String value) {
          this.subCategory = value;
     }

     @JsonProperty("subCategoryId")
     public Integer getSubCategoryID() {
          return subCategoryID;
     }

     @JsonProperty("subCategoryId")
     public void setSubCategoryID(Integer value) {
          this.subCategoryID = value;
     }

     @JsonProperty("availableQty")
     public Integer getAvailableQty() {
          return availableQty;
     }

     @JsonProperty("availableQty")
     public void setAvailableQty(Integer value) {
          this.availableQty = value;
     }

     @JsonProperty("orderQty")
     public Integer getOrderQty() {
          return orderQty;
     }

     @JsonProperty("orderQty")
     public void setOrderQty(Integer value) {
          this.orderQty = value;
     }

     @JsonProperty("cost")
     public BigDecimal getCost() {
          return cost;
     }

     @JsonProperty("cost")
     public void setCost(BigDecimal value) {
          this.cost = value;
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
