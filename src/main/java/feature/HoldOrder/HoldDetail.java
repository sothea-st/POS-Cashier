/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feature.HoldOrder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author MOBILE-APP.02
 */
public class HoldDetail {

     private int id;
     private String flag;
     private double cost;
     private int catID;
     private int qty;
     private String barcode;
     private String weight;
     private double price;
     private String proNameKh;
     private int brandID;
     private String productStatus;
     private String codeExpired;
     private String codeOutStock;
     private String proNameEn;
     private String proImageName;
     private int discount;

     @JsonProperty("id")
     public int getID() {
          return id;
     }

     @JsonProperty("id")
     public void setID(int value) {
          this.id = value;
     }

     @JsonProperty("flag")
     public String getFlag() {
          return flag;
     }

     @JsonProperty("flag")
     public void setFlag(String value) {
          this.flag = value;
     }

     @JsonProperty("cost")
     public double getCost() {
          return cost;
     }

     @JsonProperty("cost")
     public void setCost(double value) {
          this.cost = value;
     }

     @JsonProperty("cat_id")
     public int getCatID() {
          return catID;
     }

     @JsonProperty("cat_id")
     public void setCatID(int value) {
          this.catID = value;
     }

     @JsonProperty("qty")
     public int getQty() {
          return qty;
     }

     @JsonProperty("qty")
     public void setQty(int value) {
          this.qty = value;
     }

     @JsonProperty("barcode")
     public String getBarcode() {
          return barcode;
     }

     @JsonProperty("barcode")
     public void setBarcode(String value) {
          this.barcode = value;
     }

     @JsonProperty("weight")
     public String getWeight() {
          return weight;
     }

     @JsonProperty("weight")
     public void setWeight(String value) {
          this.weight = value;
     }

     @JsonProperty("price")
     public double getPrice() {
          return price;
     }

     @JsonProperty("price")
     public void setPrice(double value) {
          this.price = value;
     }

     @JsonProperty("pro_name_kh")
     public String getProNameKh() {
          return proNameKh;
     }

     @JsonProperty("pro_name_kh")
     public void setProNameKh(String value) {
          this.proNameKh = value;
     }

     @JsonProperty("brand_id")
     public int getBrandID() {
          return brandID;
     }

     @JsonProperty("brand_id")
     public void setBrandID(int value) {
          this.brandID = value;
     }

     @JsonProperty("product_status")
     public String getProductStatus() {
          return productStatus;
     }

     @JsonProperty("product_status")
     public void setProductStatus(String value) {
          this.productStatus = value;
     }

     @JsonProperty("code_expired")
     public String getCodeExpired() {
          return codeExpired;
     }

     @JsonProperty("code_expired")
     public void setCodeExpired(String value) {
          this.codeExpired = value;
     }

     @JsonProperty("code_out_stock")
     public String getCodeOutStock() {
          return codeOutStock;
     }

     @JsonProperty("code_out_stock")
     public void setCodeOutStock(String value) {
          this.codeOutStock = value;
     }

     @JsonProperty("pro_name_en")
     public String getProNameEn() {
          return proNameEn;
     }

     @JsonProperty("pro_name_en")
     public void setProNameEn(String value) {
          this.proNameEn = value;
     }

     @JsonProperty("pro_image_name")
     public String getProImageName() {
          return proImageName;
     }

     @JsonProperty("pro_image_name")
     public void setProImageName(String value) {
          this.proImageName = value;
     }

     @JsonProperty("discount")
     public int getDiscount() {
          return discount;
     }

     @JsonProperty("discount")
     public void setDiscount(int value) {
          this.discount = value;
     }
}
