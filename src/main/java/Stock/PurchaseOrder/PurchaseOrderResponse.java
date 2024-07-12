package Stock.PurchaseOrder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseOrderResponse {
     private long count;
    private PurchaseOrderDetailResponse[] data;

    @JsonProperty("count")
    public long getCount() { return count; }
    @JsonProperty("count")
    public void setCount(long value) { this.count = value; }

    @JsonProperty("data")
    public PurchaseOrderDetailResponse[] getData() { return data; }
    @JsonProperty("data")
    public void setData(PurchaseOrderDetailResponse[] value) { this.data = value; }
}
