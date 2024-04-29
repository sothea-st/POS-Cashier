package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cash {
    private int qtySaledDollar;
    private double amountSaledDollar;

    @JsonProperty("qtySaledDollar")
    public int getQtySaledDollar() { return qtySaledDollar; }
    @JsonProperty("qtySaledDollar")
    public void setQtySaledDollar(int value) { this.qtySaledDollar = value; }

    @JsonProperty("amountSaledDollar")
    public double getAmountSaledDollar() { return amountSaledDollar; }
    @JsonProperty("amountSaledDollar")
    public void setAmountSaledDollar(double value) { this.amountSaledDollar = value; }
}
