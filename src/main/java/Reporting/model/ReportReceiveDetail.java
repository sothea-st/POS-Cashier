/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reporting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 *
 * @author MOBILE-APP.02
 */
public class ReportReceiveDetail {

     private String vendorName;
     private String transactionNo;
     private String referenceNo;
     private String transactionDate;
     private String receiveBy;
     private int totalQty;
     private BigDecimal totalCost;
     private String remark;

     @JsonProperty("vendorName")
     public String getVendorName() {
          return vendorName;
     }

     @JsonProperty("vendorName")
     public void setVendorName(String value) {
          this.vendorName = value;
     }

     @JsonProperty("transactionNo")
     public String getTransactionNo() {
          return transactionNo;
     }

     @JsonProperty("transactionNo")
     public void setTransactionNo(String value) {
          this.transactionNo = value;
     }

     @JsonProperty("referenceNo")
     public String getReferenceNo() {
          return referenceNo;
     }

     @JsonProperty("referenceNo")
     public void setReferenceNo(String value) {
          this.referenceNo = value;
     }

     @JsonProperty("transactionDate")
     public String getTransactionDate() {
          return transactionDate;
     }

     @JsonProperty("transactionDate")
     public void setTransactionDate(String value) {
          this.transactionDate = value;
     }

     @JsonProperty("receiveBy")
     public String getReceiveBy() {
          return receiveBy;
     }

     @JsonProperty("receiveBy")
     public void setReceiveBy(String value) {
          this.receiveBy = value;
     }

     @JsonProperty("totalQty")
     public int getTotalQty() {
          return totalQty;
     }

     @JsonProperty("totalQty")
     public void setTotalQty(int value) {
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

     @JsonProperty("remark")
     public String getRemark() {
          return remark;
     }

     @JsonProperty("remark")
     public void setRemark(String value) {
          this.remark = value;
     }
}
