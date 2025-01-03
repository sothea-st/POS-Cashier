package Model.PointCustomer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PointCustomer {

     private String msg;
     private CustomerPointModel data;

     @JsonProperty("msg")
     public String getMsg() {
          return msg;
     }

     @JsonProperty("msg")
     public void setMsg(String value) {
          this.msg = value;
     }

     @JsonProperty("data")
     public CustomerPointModel getData() {
          return data;
     }

     @JsonProperty("data")
     public void setData(CustomerPointModel value) {
          this.data = value;
     }
}
