package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscountSummery {
    private SummerySale[] percentag;
    private Cash cash;

    @JsonProperty("percentag")
    public SummerySale[] getPercentag() { return percentag; }
    @JsonProperty("percentag")
    public void setPercentag(SummerySale[] value) { this.percentag = value; }

    @JsonProperty("cash")
    public Cash getCash() { return cash; }
    @JsonProperty("cash")
    public void setCash(Cash value) { this.cash = value; }
}
