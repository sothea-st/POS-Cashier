
package Model.ProductModelV1;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;


 
public class ProductResponseDetailV1 {

     private Integer id;
     private String subCatNameEn;
     private String proNameKh;
     private String proNameEn;
     private BigDecimal cost;
     private BigDecimal price;
     private String margin;
     private String brandNameEn;
     private String barcode;
     private Integer createBy;
     private String taxName;
     private String vendorName;
     private String uomNameEn;
     private String attrNameEn;
     private String statusName;
     private String countryImageName;
     private String choices;
     private String proImageName;
     private Integer qty;
     private String itemCode;
     private String vendorCode;

     @JsonProperty("id")
     public Integer getID() {
          return id;
     }

     @JsonProperty("id")
     public void setID(Integer value) {
          this.id = value;
     }

     @JsonProperty("subCatNameEn")
     public String getSubCatNameEn() {
          return subCatNameEn;
     }

     @JsonProperty("subCatNameEn")
     public void setSubCatNameEn(String value) {
          this.subCatNameEn = value;
     }

     @JsonProperty("proNameKh")
     public String getProNameKh() {
          return proNameKh;
     }

     @JsonProperty("proNameKh")
     public void setProNameKh(String value) {
          this.proNameKh = value;
     }

     @JsonProperty("proNameEn")
     public String getProNameEn() {
          return proNameEn;
     }

     @JsonProperty("proNameEn")
     public void setProNameEn(String value) {
          this.proNameEn = value;
     }

     @JsonProperty("cost")
     public BigDecimal getCost() {
          return cost;
     }

     @JsonProperty("cost")
     public void setCost(BigDecimal value) {
          this.cost = value;
     }

     @JsonProperty("price")
     public BigDecimal getPrice() {
          return price;
     }

     @JsonProperty("price")
     public void setPrice(BigDecimal value) {
          this.price = value;
     }

     @JsonProperty("margin")
     public String getMargin() {
          return margin;
     }

     @JsonProperty("margin")
     public void setMargin(String value) {
          this.margin = value;
     }

     @JsonProperty("brandNameEn")
     public String getBrandNameEn() {
          return brandNameEn;
     }

     @JsonProperty("brandNameEn")
     public void setBrandNameEn(String value) {
          this.brandNameEn = value;
     }

     @JsonProperty("barcode")
     public String getBarcode() {
          return barcode;
     }

     @JsonProperty("barcode")
     public void setBarcode(String value) {
          this.barcode = value;
     }

     @JsonProperty("createBy")
     public Integer getCreateBy() {
          return createBy;
     }

     @JsonProperty("createBy")
     public void setCreateBy(Integer value) {
          this.createBy = value;
     }

     @JsonProperty("taxName")
     public String getTaxName() {
          return taxName;
     }

     @JsonProperty("taxName")
     public void setTaxName(String value) {
          this.taxName = value;
     }

     @JsonProperty("vendorName")
     public String getVendorName() {
          return vendorName;
     }

     @JsonProperty("vendorName")
     public void setVendorName(String value) {
          this.vendorName = value;
     }

     @JsonProperty("uomNameEn")
     public String getUomNameEn() {
          return uomNameEn;
     }

     @JsonProperty("uomNameEn")
     public void setUomNameEn(String value) {
          this.uomNameEn = value;
     }

     @JsonProperty("attrNameEn")
     public String getAttrNameEn() {
          return attrNameEn;
     }

     @JsonProperty("attrNameEn")
     public void setAttrNameEn(String value) {
          this.attrNameEn = value;
     }

     @JsonProperty("statusName")
     public String getStatusName() {
          return statusName;
     }

     @JsonProperty("statusName")
     public void setStatusName(String value) {
          this.statusName = value;
     }

     @JsonProperty("countryImageName")
     public String getCountryImageName() {
          return countryImageName;
     }

     @JsonProperty("countryImageName")
     public void setCountryImageName(String value) {
          this.countryImageName = value;
     }

     @JsonProperty("choices")
     public String getChoices() {
          return choices;
     }

     @JsonProperty("choices")
     public void setChoices(String value) {
          this.choices = value;
     }

     @JsonProperty("proImageName")
     public String getProImageName() {
          return proImageName;
     }

     @JsonProperty("proImageName")
     public void setProImageName(String value) {
          this.proImageName = value;
     }

     @JsonProperty("qty")
     public Integer getQty() {
          return qty;
     }

     @JsonProperty("qty")
     public void setQty(Integer value) {
          this.qty = value;
     }
     
     @JsonProperty("itemCode")
     public String getItemCode() {
          return itemCode;
     }

     @JsonProperty("itemCode")
     public void setItemCode(String itemCode) {
          this.itemCode = itemCode;
     }

     @JsonProperty("vendorCode")
     public String getVendorCode() {
          return vendorCode;
     }

     @JsonProperty("vendorCode")
     public void setVendorCode(String vendorCode) {
          this.vendorCode = vendorCode;
     }
}
