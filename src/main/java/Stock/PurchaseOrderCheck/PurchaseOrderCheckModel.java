package Stock.PurchaseOrderCheck;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseOrderCheckModel {
     private long status;
     private String msg;
     private POCheckDetailsModel data;

     @JsonProperty("status")
     public long getStatus() {
          return status;
     }

     @JsonProperty("status")
     public void setStatus(long value) {
          this.status = value;
     }

     @JsonProperty("msg")
     public String getMsg() {
          return msg;
     }

     @JsonProperty("msg")
     public void setMsg(String value) {
          this.msg = value;
     }

     @JsonProperty("data")
     public POCheckDetailsModel getData() {
          return data;
     }

     @JsonProperty("data")
     public void setData(POCheckDetailsModel value) {
          this.data = value;
     }
}
