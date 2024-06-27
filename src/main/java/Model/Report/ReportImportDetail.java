/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

 
public class ReportImportDetail {

     private BigDecimal amount;
     private String proNameEn;
     private BigDecimal discount;
     private BigDecimal total;
     private double cost;
     private String fullName;
     private int proID;
     private int qtyOld;
     private String impDate;
     private String proImageName;

    @JsonProperty("amount")
    public BigDecimal getAmount() { return amount; }
    @JsonProperty("amount")
    public void setAmount(BigDecimal value) { this.amount = value; }

    @JsonProperty("pro_name_en")
    public String getProNameEn() { return proNameEn; }
    @JsonProperty("pro_name_en")
    public void setProNameEn(String value) { this.proNameEn = value; }

    @JsonProperty("discount")
    public BigDecimal getDiscount() { return discount; }
    @JsonProperty("discount")
    public void setDiscount(BigDecimal value) { this.discount = value; }

    @JsonProperty("total")
    public BigDecimal getTotal() { return total; }
    @JsonProperty("total")
    public void setTotal(BigDecimal value) { this.total = value; }

    @JsonProperty("cost")
    public double getCost() { return cost; }
    @JsonProperty("cost")
    public void setCost(double value) { this.cost = value; }

    @JsonProperty("full_name")
    public String getFullName() { return fullName; }
    @JsonProperty("full_name")
    public void setFullName(String value) { this.fullName = value; }

    @JsonProperty("pro_id")
    public int getProID() { return proID; }
    @JsonProperty("pro_id")
    public void setProID(int value) { this.proID = value; }

    @JsonProperty("qty_old")
    public int getQtyOld() { return qtyOld; }
    @JsonProperty("qty_old")
    public void setQtyOld(int value) { this.qtyOld = value; }

    @JsonProperty("imp_date")
    public String getImpDate() { return impDate; }
    @JsonProperty("imp_date")
    public void setImpDate(String value) { this.impDate = value; }

    @JsonProperty("pro_image_name")
    public String getProImageName() { return proImageName; }
    @JsonProperty("pro_image_name")
    public void setProImageName(String value) { this.proImageName = value; }
}
