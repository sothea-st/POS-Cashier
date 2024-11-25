package com.example.pos.system.domain.models;
import java.math.*;
public class SummeryCashierReport {
    private String title;
    private int saleOfNum;
    private BigDecimal total;

    public SummeryCashierReport(){

    }

    public SummeryCashierReport(String title , int saleOfNum , BigDecimal total) {
        this.title = title;
        this.saleOfNum = saleOfNum ;
        this.total = total;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setSaleOfNum(int saleOfNum) {
        this.saleOfNum = saleOfNum;
    }
    public int getSaleOfNum(){
        return saleOfNum;
    }

    public BigDecimal getTotal(){
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


}
