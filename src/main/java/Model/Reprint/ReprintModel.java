package Model.Reprint;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class ReprintModel {

     private String remainingKhr;
     private String companyLogo;
     private String companyName;
     private double receiveKhr;
     private double changeUsd;
     private String companyAddres;
     private SaleDetailModel[] saleDetails;
     private long remainingUsd;
     private String saleDate;
     private String paymentNo;
     private String paymentBarcode;
     private double total;
     private String customerType;
     private String empName;
     private double changeKhr;
     private double receiveUsd;
     private String companyContact;
     private String vattin;
     private String returned;

     public ReprintModel() {
     }

     public ReprintModel(
          String remainingKhr,
          String companyLogo,
          String companyName,
          double receiveKhr,
          double changeUsd,
          String companyAddres,
          SaleDetailModel[] saleDetails,
          long remainingUsd,
          String saleDate,
          String paymentNo,
          double total,
          String customerType,
          String empName,
          double changeKhr,
          double receiveUsd,
          String companyContact,
          String vattin,
          String returned,
          String paymentBarcode
          ) {
          this.remainingKhr = remainingKhr;
          this.companyLogo = companyLogo;
          this.companyName = companyName;
          this.receiveKhr = receiveKhr;
          this.changeUsd = changeUsd;
          this.companyAddres = companyAddres;
          this.saleDetails = saleDetails;
          this.remainingUsd = remainingUsd;
          this.saleDate = saleDate;
          this.paymentNo = paymentNo;
          this.total = total;
          this.customerType = customerType;
          this.empName = empName;
          this.changeKhr = changeKhr;
          this.receiveUsd = receiveUsd;
          this.companyContact = companyContact;
          this.vattin = vattin;
          this.returned = returned;
          this.paymentBarcode = paymentBarcode;
     }

     @JsonProperty("remainingKhr")
     public String getRemainingKhr() {
          return remainingKhr;
     }

     @JsonProperty("remainingKhr")
     public void setRemainingKhr(String value) {
          this.remainingKhr = value;
     }

     @JsonProperty("companyLogo")
     public String getCompanyLogo() {
          return companyLogo;
     }

     @JsonProperty("companyLogo")
     public void setCompanyLogo(String value) {
          this.companyLogo = value;
     }

     @JsonProperty("companyName")
     public String getCompanyName() {
          return companyName;
     }

     @JsonProperty("companyName")
     public void setCompanyName(String value) {
          this.companyName = value;
     }

     @JsonProperty("receiveKhr")
     public double getReceiveKhr() {
          return receiveKhr;
     }

     @JsonProperty("receiveKhr")
     public void setReceiveKhr(double value) {
          this.receiveKhr = value;
     }

     @JsonProperty("changeUsd")
     public double getChangeUsd() {
          return changeUsd;
     }

     @JsonProperty("changeUsd")
     public void setChangeUsd(double value) {
          this.changeUsd = value;
     }

     @JsonProperty("companyAddres")
     public String getCompanyAddres() {
          return companyAddres;
     }

     @JsonProperty("companyAddres")
     public void setCompanyAddres(String value) {
          this.companyAddres = value;
     }

     @JsonProperty("saleDetails")
     public SaleDetailModel[] getSaleDetails() {
          return saleDetails;
     }

     @JsonProperty("saleDetails")
     public void setSaleDetails(SaleDetailModel[] value) {
          this.saleDetails = value;
     }

     @JsonProperty("remainingUsd")
     public long getRemainingUsd() {
          return remainingUsd;
     }

     @JsonProperty("remainingUsd")
     public void setRemainingUsd(long value) {
          this.remainingUsd = value;
     }

     @JsonProperty("saleDate")
     public String getSaleDate() {
          return saleDate;
     }

     @JsonProperty("saleDate")
     public void setSaleDate(String value) {
          this.saleDate = value;
     }

     @JsonProperty("paymentNo")
     public String getPaymentNo() {
          return paymentNo;
     }

     @JsonProperty("paymentNo")
     public void setPaymentNo(String value) {
          this.paymentNo = value;
     }
     
     
     @JsonProperty("paymentBarcode")
     public String getPaymentBarcode() {
          return paymentBarcode;
     }

     @JsonProperty("paymentBarcode")
     public void setPaymentBarcode(String value) {
          this.paymentBarcode = value;
     }

     @JsonProperty("total")
     public double getTotal() {
          return total;
     }

     @JsonProperty("total")
     public void setTotal(double value) {
          this.total = value;
     }

     @JsonProperty("customerType")
     public String getCustomerType() {
          return customerType;
     }

     @JsonProperty("customerType")
     public void setCustomerType(String value) {
          this.customerType = value;
     }

     @JsonProperty("empName")
     public String getEmpName() {
          return empName;
     }

     @JsonProperty("empName")
     public void setEmpName(String value) {
          this.empName = value;
     }

     @JsonProperty("changeKhr")
     public double getChangeKhr() {
          return changeKhr;
     }

     @JsonProperty("changeKhr")
     public void setChangeKhr(double value) {
          this.changeKhr = value;
     }

     @JsonProperty("receiveUsd")
     public double getReceiveUsd() {
          return receiveUsd;
     }

     @JsonProperty("receiveUsd")
     public void setReceiveUsd(double value) {
          this.receiveUsd = value;
     }

     @JsonProperty("companyContact")
     public String getCompanyContact() {
          return companyContact;
     }

     @JsonProperty("companyContact")
     public void setCompanyContact(String value) {
          this.companyContact = value;
     }

     @JsonProperty("vattin")
     public String getVattin() {
          return vattin;
     }

     @JsonProperty("vattin")
     public void setVattin(String vattin) {
          this.vattin = vattin;
     }

     @JsonProperty("returned")
     public String getReturned() {
          return returned;
     }

     @JsonProperty("returned")
     public void setReturned(String returned) {
          this.returned = returned;
     }

}
