package Model.ReturnModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelReturnData {

     private ResultDataReturnModel[] data;
     private String invoiceNo;
     private String msg;

     @JsonProperty("data")
     public ResultDataReturnModel[] getData() {
          return data;
     }

     @JsonProperty("data")
     public void setData(ResultDataReturnModel[] value) {
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
