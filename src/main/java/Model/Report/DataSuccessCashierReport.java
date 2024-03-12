package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataSuccessCashierReport {
    private Data data;
    private String msg;

    @JsonProperty("data")
    public Data getData() { return data; }
    @JsonProperty("data")
    public void setData(Data value) { this.data = value; }

    @JsonProperty("msg")
    public String getMsg() { return msg; }
    @JsonProperty("msg")
    public void setMsg(String value) { this.msg = value; }
}
