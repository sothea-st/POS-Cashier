package Model.ReturnModel;

import Model.ProductModel.ProductDataModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelReturnData {

     public static Double receive_usd = null;
     public static String receive_khr = null;
     public static Double change_usd = null;
     public static String change_khr = null;

     public static void setReceiveToNull() {
          receive_khr = null;
          receive_usd = null;
          change_usd = null;
          change_khr = null;
     }

     private ProductDataModel[] data;
     private String invoiceNo;
     private String msg;
     private Double receiveUsd;
     private String receiveKhr;
     private String changeKhr;
     private Double changeUsd;
     private Integer saleId;

     @JsonProperty("saleId")
     public Integer getSaleId() {
          return saleId;
     }

     @JsonProperty("saleId")
     public void setSaleId(Integer saleId) {
          this.saleId = saleId;
     }

     @JsonProperty("changeKhr")
     public String getChangeKhr() {
          return changeKhr;
     }

     @JsonProperty("changeKhr")
     public void setChangeKhr(String changeKhr) {
          this.changeKhr = changeKhr;
     }

     @JsonProperty("changeUsd")
     public Double getChangeUsd() {
          return changeUsd;
     }

     @JsonProperty("changeUsd")
     public void setChangeUsd(Double changeUsd) {
          this.changeUsd = changeUsd;
     }

     @JsonProperty("receiveUsd")
     public Double getReceiveUsd() {
          return receiveUsd;
     }

     @JsonProperty("receiveUsd")
     public void setReceiveUsd(Double receiveUsd) {
          this.receiveUsd = receiveUsd;
     }

     @JsonProperty("receiveKhr")
     public String getReceiveKhr() {
          return receiveKhr;
     }

     @JsonProperty("receiveKhr")
     public void setReceiveKhr(String receiveKhr) {
          this.receiveKhr = receiveKhr;
     }

     @JsonProperty("data")
     public ProductDataModel[] getData() {
          return data;
     }

     @JsonProperty("data")
     public void setData(ProductDataModel[] value) {
          this.data = value;
     }

     @JsonProperty("invoiceNo")
     public String getInvoiceNo() {
          return invoiceNo;
     }

     @JsonProperty("invoiceNo")
     public void setInvoiceNo(String value) {
          this.invoiceNo = value;
     }

     @JsonProperty("msg")
     public String getMsg() {
          return msg;
     }

     @JsonProperty("msg")
     public void setMsg(String value) {
          this.msg = value;
     }
}
