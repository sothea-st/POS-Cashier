package Model.ProductModelV1;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponseByIdV1 {
     private Integer status;
     private String msg;
     private Data data;

     @JsonProperty("status")
     public Integer getStatus() {
          return status;
     }

     @JsonProperty("status")
     public void setStatus(Integer value) {
          this.status = value;
     }

     @JsonProperty("msg")
     public String getMsg() {
          return msg;
     }

     @JsonProperty("msg")
     public void setMsg(String value) {
          this.msg = value;
     }

     @JsonProperty("data")
     public Data getData() {
          return data;
     }

     @JsonProperty("data")
     public void setData(Data value) {
          this.data = value;
     }

     public class Data {
          private Integer id;
          private Integer subCatID;
          private String proNameKh;
          private String proNameEn;
          private Integer cost;
          private Integer price;
          private String margin;
          private Integer brandID;
          private String barcode;
          private Integer createBy;
          private Integer taxID;
          private Integer vendorID;
          private Integer uomID;
          private Integer attributeID;
          private Integer productActiveID;
          private Integer countryID;
          private String choices;
          private String proImageName;
          private Integer qty;

          @JsonProperty("id")
          public Integer getID() {
               return id;
          }

          @JsonProperty("id")
          public void setID(Integer value) {
               this.id = value;
          }

          @JsonProperty("subCatId")
          public Integer getSubCatID() {
               return subCatID;
          }

          @JsonProperty("subCatId")
          public void setSubCatID(Integer value) {
               this.subCatID = value;
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
          public Integer getCost() {
               return cost;
          }

          @JsonProperty("cost")
          public void setCost(Integer value) {
               this.cost = value;
          }

          @JsonProperty("price")
          public Integer getPrice() {
               return price;
          }

          @JsonProperty("price")
          public void setPrice(Integer value) {
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

          @JsonProperty("brandId")
          public Integer getBrandID() {
               return brandID;
          }

          @JsonProperty("brandId")
          public void setBrandID(Integer value) {
               this.brandID = value;
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

          @JsonProperty("taxId")
          public Integer getTaxID() {
               return taxID;
          }

          @JsonProperty("taxId")
          public void setTaxID(Integer value) {
               this.taxID = value;
          }

          @JsonProperty("vendorId")
          public Integer getVendorID() {
               return vendorID;
          }

          @JsonProperty("vendorId")
          public void setVendorID(Integer value) {
               this.vendorID = value;
          }

          @JsonProperty("uomId")
          public Integer getUomID() {
               return uomID;
          }

          @JsonProperty("uomId")
          public void setUomID(Integer value) {
               this.uomID = value;
          }

          @JsonProperty("attributeId")
          public Integer getAttributeID() {
               return attributeID;
          }

          @JsonProperty("attributeId")
          public void setAttributeID(Integer value) {
               this.attributeID = value;
          }

          @JsonProperty("productActiveId")
          public Integer getProductActiveID() {
               return productActiveID;
          }

          @JsonProperty("productActiveId")
          public void setProductActiveID(Integer value) {
               this.productActiveID = value;
          }

          @JsonProperty("countryId")
          public Integer getCountryID() {
               return countryID;
          }

          @JsonProperty("countryId")
          public void setCountryID(Integer value) {
               this.countryID = value;
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
     }
}
