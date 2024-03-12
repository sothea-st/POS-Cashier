package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummaryVat {
    private String title;
    private double total;

    @JsonProperty("title")
    public String getTitle() { return title; }
    @JsonProperty("title")
    public void setTitle(String value) { this.title = value; }

    @JsonProperty("total")
    public double getTotal() { return total; }
    @JsonProperty("total")
    public void setTotal(double value) { this.total = value; }
}
