
package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummerySale {
    private String title;
    private int saleOfNum;
    private double total;

    @JsonProperty("title")
    public String getTitle() { return title; }
    @JsonProperty("title")
    public void setTitle(String value) { this.title = value; }

    @JsonProperty("saleOfNum")
    public int getSaleOfNum() { return saleOfNum; }
    @JsonProperty("saleOfNum")
    public void setSaleOfNum(int value) { this.saleOfNum = value; }

    @JsonProperty("total")
    public double getTotal() { return total; }
    @JsonProperty("total")
    public void setTotal(double value) { this.total = value; }
}
