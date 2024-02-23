
package Model.HoldOrder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HoldOrder {
    private String msg;
    private DataHoldOrder[] data;

    @JsonProperty("msg")
    public String getMsg() { return msg; }
    @JsonProperty("msg")
    public void setMsg(String value) { this.msg = value; }

    @JsonProperty("data")
    public DataHoldOrder[] getData() { return data; }
    @JsonProperty("data")
    public void setData(DataHoldOrder[] value) { this.data = value; }
}
