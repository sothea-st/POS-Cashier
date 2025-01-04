package Constant.DefaultPrice;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author FRONT-END.06
 */
public class DataModelDefaultPrice {
 private String msg;
    private DefaultPrice[] data;

    @JsonProperty("msg")
    public String getMsg() { return msg; }
    @JsonProperty("msg")
    public void setMsg(String value) { this.msg = value; }

    @JsonProperty("data")
    public DefaultPrice[] getData() { return data; }
    @JsonProperty("data")
    public void setData(DefaultPrice[] value) { this.data = value; }
}
