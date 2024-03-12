package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummaryPayemnt {
    private long qtySaledDollar;
    private long amountSaledDollar;

    @JsonProperty("qtySaledDollar")
    public long getQtySaledDollar() { return qtySaledDollar; }
    @JsonProperty("qtySaledDollar")
    public void setQtySaledDollar(long value) { this.qtySaledDollar = value; }

    @JsonProperty("amountSaledDollar")
    public long getAmountSaledDollar() { return amountSaledDollar; }
    @JsonProperty("amountSaledDollar")
    public void setAmountSaledDollar(long value) { this.amountSaledDollar = value; }
}
