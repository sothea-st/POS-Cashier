
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HoldOrder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author MOBILE-APP.02
 */
public class HoldProductModel {

     private int id;
     private String flag;
     private double discount;
     private int qty;
     private double cost;
     private double price;
     private int catID;
     private String barcode;
     private String weight;
     private String discountType;
     private String proNameEn;
     private String codeExpired;
     private String codeOutStock;
     private int brandID;
     private String proNameKh;
     private String proImageName;
     private String productStatus;

     public HoldProductModel() {
     }

     public HoldProductModel(int id, String flag, double discount, int qty, double cost, double price, int catID, String barcode, String weight, String discountType, String proNameEn, String codeExpired, String codeOutStock, int brandID, String proNameKh, String proImageName, String productStatus) {
          this.id = id;
          this.flag = flag;
          this.discount = discount;
          this.qty = qty;
          this.cost = cost;
          this.price = price;
          this.catID = catID;
          this.barcode = barcode;
          this.weight = weight;
          this.discountType = discountType;
          this.proNameEn = proNameEn;
          this.codeExpired = codeExpired;
          this.codeOutStock = codeOutStock;
          this.brandID = brandID;
          this.proNameKh = proNameKh;
          this.proImageName = proImageName;
          this.productStatus = productStatus;
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getFlag() {
          return flag;
     }

     public void setFlag(String flag) {
          this.flag = flag;
     }

     public double getDiscount() {
          return discount;
     }

     public void setDiscount(double discount) {
          this.discount = discount;
     }

     public int getQty() {
          return qty;
     }

     public void setQty(int qty) {
          this.qty = qty;
     }

     public double getCost() {
          return cost;
     }

     public void setCost(double cost) {
          this.cost = cost;
     }

     public double getPrice() {
          return price;
     }

     public void setPrice(double price) {
          this.price = price;
     }

     public int getCatID() {
          return catID;
     }

     public void setCatID(int catID) {
          this.catID = catID;
     }

     public String getBarcode() {
          return barcode;
     }

     public void setBarcode(String barcode) {
          this.barcode = barcode;
     }

     public String getWeight() {
          return weight;
     }

     public void setWeight(String weight) {
          this.weight = weight;
     }

     public String getDiscountType() {
          return discountType;
     }

     public void setDiscountType(String discountType) {
          this.discountType = discountType;
     }

     public String getProNameEn() {
          return proNameEn;
     }

     public void setProNameEn(String proNameEn) {
          this.proNameEn = proNameEn;
     }

     public String getCodeExpired() {
          return codeExpired;
     }

     public void setCodeExpired(String codeExpired) {
          this.codeExpired = codeExpired;
     }

     public String getCodeOutStock() {
          return codeOutStock;
     }

     public void setCodeOutStock(String codeOutStock) {
          this.codeOutStock = codeOutStock;
     }

     public int getBrandID() {
          return brandID;
     }

     public void setBrandID(int brandID) {
          this.brandID = brandID;
     }

     public String getProNameKh() {
          return proNameKh;
     }

     public void setProNameKh(String proNameKh) {
          this.proNameKh = proNameKh;
     }

     public String getProImageName() {
          return proImageName;
     }

     public void setProImageName(String proImageName) {
          this.proImageName = proImageName;
     }

     public String getProductStatus() {
          return productStatus;
     }

     public void setProductStatus(String productStatus) {
          this.productStatus = productStatus;
     }
     
     
     
     
     

}
