/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ReturnModel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author MOBILE-APP.02
 */
public class ResultDataReturnModel {

     private int brandID;
     private String proNameKh;
     private String proImageName;
     private String productStatus;
     private String proNameEn;
     private int id;
     private String flag;
     private int discount;
     private double cost;
     private double price;
     private String weight;
     private String barcode;
     private int catID;
     private String codeExpired;
     private String codeOutStock;
     private int qty;

     @JsonProperty("brandId")
     public int getBrandID() {
          return brandID;
     }

     @JsonProperty("brandId")
     public void setBrandID(int value) {
          this.brandID = value;
     }

     @JsonProperty("proNameKh")
     public String getProNameKh() {
          return proNameKh;
     }

     @JsonProperty("proNameKh")
     public void setProNameKh(String value) {
          this.proNameKh = value;
     }

     @JsonProperty("proImageName")
     public String getProImageName() {
          return proImageName;
     }

     @JsonProperty("proImageName")
     public void setProImageName(String value) {
          this.proImageName = value;
     }

     @JsonProperty("productStatus")
     public String getProductStatus() {
          return productStatus;
     }

     @JsonProperty("productStatus")
     public void setProductStatus(String value) {
          this.productStatus = value;
     }

     @JsonProperty("proNameEn")
     public String getProNameEn() {
          return proNameEn;
     }

     @JsonProperty("proNameEn")
     public void setProNameEn(String value) {
          this.proNameEn = value;
     }

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

     @JsonProperty("discount")
     public int getDiscount() {
          return discount;
     }

     @JsonProperty("discount")
     public void setDiscount(int value) {
          this.discount = value;
     }

     @JsonProperty("cost")
     public double getCost() {
          return cost;
     }

     @JsonProperty("cost")
     public void setCost(double value) {
          this.cost = value;
     }

     @JsonProperty("price")
     public double getPrice() {
          return price;
     }

     @JsonProperty("price")
     public void setPrice(double value) {
          this.price = value;
     }

     @JsonProperty("weight")
     public String getWeight() {
          return weight;
     }

     @JsonProperty("weight")
     public void setWeight(String value) {
          this.weight = value;
     }

     @JsonProperty("barcode")
     public String getBarcode() {
          return barcode;
     }

     @JsonProperty("barcode")
     public void setBarcode(String value) {
          this.barcode = value;
     }

     @JsonProperty("catID")
     public int getCatID() {
          return catID;
     }

     @JsonProperty("catID")
     public void setCatID(int value) {
          this.catID = value;
     }

     @JsonProperty("codeExpired")
     public String getCodeExpired() {
          return codeExpired;
     }

     @JsonProperty("codeExpired")
     public void setCodeExpired(String value) {
          this.codeExpired = value;
     }

     @JsonProperty("codeOutStock")
     public String getCodeOutStock() {
          return codeOutStock;
     }

     @JsonProperty("codeOutStock")
     public void setCodeOutStock(String value) {
          this.codeOutStock = value;
     }

     @JsonProperty("qty")
     public int getQty() {
          return qty;
     }

     @JsonProperty("qty")
     public void setQty(int value) {
          this.qty = value;
     }
}
