package Constant;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDetail {

     private long code;
     private String reason;

     @JsonProperty("code")
     public long getCode() {
          return code;
     }

     @JsonProperty("code")
     public void setCode(long value) {
          this.code = value;
          
     }

     @JsonProperty("reason")
     public String getReason() {
          return reason;
     }

     @JsonProperty("reason")
     public void setReason(String value) {
          this.reason = value;
     }
}
