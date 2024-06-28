package Constant;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

     private ErrorDetail error;

     @JsonProperty("error")
     public ErrorDetail getError() {
          return error;
     }

     @JsonProperty("error")
     public void setError(ErrorDetail value) {
          this.error = value;
     }
}

