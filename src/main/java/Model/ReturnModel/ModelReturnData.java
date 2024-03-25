package Model.ReturnModel;

import Model.ProductModel.ProductDataModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelReturnData {

     private ProductDataModel[] data;
     private String invoiceNo;
     private String msg;

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
