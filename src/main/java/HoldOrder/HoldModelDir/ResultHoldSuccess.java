
package HoldOrder.HoldModelDir;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ResultHoldSuccess {

     private String msg;
     private DataListHold[] data;
     private int count;

     @JsonProperty("msg")
     public String getMsg() {
          return msg;
     }

     @JsonProperty("msg")
     public void setMsg(String value) {
          this.msg = value;
     }

     @JsonProperty("data")
     public DataListHold[] getData() {
          return data;
     }

     @JsonProperty("data")
     public void setData(DataListHold[] value) {
          this.data = value;
     }

     @JsonProperty("count")
     public int getCount() {
          return count;
     }

     @JsonProperty("count")
     public void setCount(int value) {
          this.count = value;
     }
}
