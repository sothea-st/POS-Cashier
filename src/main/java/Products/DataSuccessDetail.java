
package Products;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DataSuccessDetail {
    private ListDetailProduct data;
    private String msg;

    @JsonProperty("data")
    public ListDetailProduct getData() { return data; }
    @JsonProperty("data")
    public void setData(ListDetailProduct value) { this.data = value; }

    @JsonProperty("msg")
    public String getMsg() { return msg; }
    @JsonProperty("msg")
    public void setMsg(String value) { this.msg = value; }
}
