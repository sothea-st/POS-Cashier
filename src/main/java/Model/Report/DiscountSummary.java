package Model.Report;
 
import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscountSummary {
    private SummarySale[] percentag;
    private SummaryPayemnt cash;

    @JsonProperty("percentag")
    public SummarySale[] getPercentag() { return percentag; }
    @JsonProperty("percentag")
    public void setPercentag(SummarySale[] value) { this.percentag = value; }

    @JsonProperty("cash")
    public SummaryPayemnt getCash() { return cash; }
    @JsonProperty("cash")
    public void setCash(SummaryPayemnt value) { this.cash = value; }
}
