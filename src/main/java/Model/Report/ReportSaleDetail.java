/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
 
/**
 *
 * @author MOBILE-APP.02
 */
public class ReportSaleDetail {

     private String saleDate;
     private String proNameEn;
     private String proImageName;
     private int qty;
     private Object discountCase;
     private BigDecimal discountPercentage;
     private BigDecimal discount;
     private BigDecimal price;
     private BigDecimal amountWithTax;
     private String taxType;
     private BigDecimal totalSaledExcludeVAT;
     private BigDecimal vatAmt;
     private BigDecimal plt;
     private BigDecimal netSale;
     private BigDecimal cost;
     private BigDecimal margin;
     private String userName;
     private String barcode;

     @JsonProperty("saleDate")
     public String getSaleDate() {
          return saleDate;
     }

     @JsonProperty("saleDate")
     public void setSaleDate(String value) {
          this.saleDate = value;
     }

     @JsonProperty("proNameEn")
     public String getProNameEn() {
          return proNameEn;
     }

     @JsonProperty("proNameEn")
     public void setProNameEn(String value) {
          this.proNameEn = value;
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
     public int getQty() {
          return qty;
     }

     @JsonProperty("qty")
     public void setQty(int value) {
          this.qty = value;
     }

     @JsonProperty("discountCase")
     public Object getDiscountCase() {
          return discountCase;
     }

     @JsonProperty("discountCase")
     public void setDiscountCase(Object value) {
          this.discountCase = value;
     }

     @JsonProperty("discountPercentage")
     public BigDecimal getDiscountPercentage() {
          return discountPercentage;
     }

     @JsonProperty("discountPercentage")
     public void setDiscountPercentage(BigDecimal value) {
          this.discountPercentage = value;
     }

     @JsonProperty("discount")
     public BigDecimal getDiscount() {
          return discount;
     }

     @JsonProperty("discount")
     public void setDiscount(BigDecimal value) {
          this.discount = value;
     }

     @JsonProperty("price")
     public BigDecimal getPrice() {
          return price;
     }

     @JsonProperty("price")
     public void setPrice(BigDecimal value) {
          this.price = value;
     }

     @JsonProperty("amountWithTax")
     public BigDecimal getAmountWithTax() {
          return amountWithTax;
     }

     @JsonProperty("amountWithTax")
     public void setAmountWithTax(BigDecimal value) {
          this.amountWithTax = value;
     }

     @JsonProperty("taxType")
     public String getTaxType() {
          return taxType;
     }

     @JsonProperty("taxType")
     public void setTaxType(String value) {
          this.taxType = value;
     }

     @JsonProperty("totalSaledExcludeVAT")
     public BigDecimal getTotalSaledExcludeVAT() {
          return totalSaledExcludeVAT;
     }

     @JsonProperty("totalSaledExcludeVAT")
     public void setTotalSaledExcludeVAT(BigDecimal value) {
          this.totalSaledExcludeVAT = value;
     }

     @JsonProperty("vatAmt")
     public BigDecimal getVatAmt() {
          return vatAmt;
     }

     @JsonProperty("vatAmt")
     public void setVatAmt(BigDecimal value) {
          this.vatAmt = value;
     }

     @JsonProperty("plt")
     public BigDecimal getPLT() {
          return plt;
     }

     @JsonProperty("plt")
     public void setPLT(BigDecimal value) {
          this.plt = value;
     }

     @JsonProperty("netSale")
     public BigDecimal getNetSale() {
          return netSale;
     }

     @JsonProperty("netSale")
     public void setNetSale(BigDecimal value) {
          this.netSale = value;
     }

     @JsonProperty("cost")
     public BigDecimal getCost() {
          return cost;
     }

     @JsonProperty("cost")
     public void setCost(BigDecimal value) {
          this.cost = value;
     }

     @JsonProperty("margin")
     public BigDecimal getMargin() {
          return margin;
     }

     @JsonProperty("margin")
     public void setMargin(BigDecimal value) {
          this.margin = value;
     }

     @JsonProperty("userName")
     public String getUserName() {
          return userName;
     }

     @JsonProperty("userName")
     public void setUserName(String value) {
          this.userName = value;
     }

     @JsonProperty("barcode")
     public String getBarcode() {
          return barcode;
     }

     @JsonProperty("barcode")
     public void setBarcode(String value) {
          this.barcode = value;
     }
}
