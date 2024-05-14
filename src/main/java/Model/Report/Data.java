package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    private double openCashKhr;
    private String paymentNoFirst;
    private String companyLogo;
    private String companyName;
    private double totalWithdrawal;
    private SummeryVat[] summeryVat;
    private String userName;
    private DiscountSummery discountSummery;
    private String closeDate;
    private String posID;
    private double openCashUsd;
    private String paymentNoLast;
    private double cashierTotal;
    private SummerySale[] summeryPayemnt;
    private String companyAddress;
    private SummerySale[] summerySale;
    private String companyContact;
    private String openDate;
    private double closeCash;
//    private double cashierCount;
    private SummeryVat[] summeryAllProVat;

    @JsonProperty("openCashKhr")
    public double getOpenCashKhr() { return openCashKhr; }
    @JsonProperty("openCashKhr")
    public void setOpenCashKhr(double value) { this.openCashKhr = value; }

    @JsonProperty("paymentNoFirst")
    public String getPaymentNoFirst() { return paymentNoFirst; }
    @JsonProperty("paymentNoFirst")
    public void setPaymentNoFirst(String value) { this.paymentNoFirst = value; }

    @JsonProperty("companyLogo")
    public String getCompanyLogo() { return companyLogo; }
    @JsonProperty("companyLogo")
    public void setCompanyLogo(String value) { this.companyLogo = value; }

    @JsonProperty("companyName")
    public String getCompanyName() { return companyName; }
    @JsonProperty("companyName")
    public void setCompanyName(String value) { this.companyName = value; }

    @JsonProperty("totalWithdrawal")
    public double getTotalWithdrawal() { return totalWithdrawal; }
    @JsonProperty("totalWithdrawal")
    public void setTotalWithdrawal(double value) { this.totalWithdrawal = value; }

    @JsonProperty("SummeryVat")
    public SummeryVat[] getSummeryVat() { return summeryVat; }
    @JsonProperty("SummeryVat")
    public void setSummeryVat(SummeryVat[] value) { this.summeryVat = value; }

    @JsonProperty("userName")
    public String getUserName() { return userName; }
    @JsonProperty("userName")
    public void setUserName(String value) { this.userName = value; }

    @JsonProperty("discountSummery")
    public DiscountSummery getDiscountSummery() { return discountSummery; }
    @JsonProperty("discountSummery")
    public void setDiscountSummery(DiscountSummery value) { this.discountSummery = value; }

    @JsonProperty("closeDate")
    public String getCloseDate() { return closeDate; }
    @JsonProperty("closeDate")
    public void setCloseDate(String value) { this.closeDate = value; }

    @JsonProperty("posId")
    public String getPosID() { return posID; }
    @JsonProperty("posId")
    public void setPosID(String value) { this.posID = value; }

    @JsonProperty("openCashUsd")
    public double getOpenCashUsd() { return openCashUsd; }
    @JsonProperty("openCashUsd")
    public void setOpenCashUsd(double value) { this.openCashUsd = value; }

    @JsonProperty("paymentNoLast")
    public String getPaymentNoLast() { return paymentNoLast; }
    @JsonProperty("paymentNoLast")
    public void setPaymentNoLast(String value) { this.paymentNoLast = value; }

    @JsonProperty("cashierTotal")
    public double getCashierTotal() { return cashierTotal; }
    @JsonProperty("cashierTotal")
    public void setCashierTotal(double value) { this.cashierTotal = value; }

    @JsonProperty("summeryPayemnt")
    public SummerySale[] getSummeryPayemnt() { return summeryPayemnt; }
    @JsonProperty("summeryPayemnt")
    public void setSummeryPayemnt(SummerySale[] value) { this.summeryPayemnt = value; }

    @JsonProperty("companyAddress")
    public String getCompanyAddress() { return companyAddress; }
    @JsonProperty("companyAddress")
    public void setCompanyAddress(String value) { this.companyAddress = value; }

    @JsonProperty("SummerySale")
    public SummerySale[] getSummerySale() { return summerySale; }
    @JsonProperty("SummerySale")
    public void setSummerySale(SummerySale[] value) { this.summerySale = value; }

    @JsonProperty("companyContact")
    public String getCompanyContact() { return companyContact; }
    @JsonProperty("companyContact")
    public void setCompanyContact(String value) { this.companyContact = value; }

    @JsonProperty("openDate")
    public String getOpenDate() { return openDate; }
    @JsonProperty("openDate")
    public void setOpenDate(String value) { this.openDate = value; }

    @JsonProperty("closeCash")
    public double getCloseCash() { return closeCash; }
    @JsonProperty("closeCash")
    public void setCloseCash(double value) { this.closeCash = value; }
    
//    @JsonProperty("cashierCount")
//    public double getCashierCount() { return cashierCount; }
//    @JsonProperty("cashierCount")
//    public void setCashierCount(double value) { this.cashierCount = value; }

    @JsonProperty("SummeryAllProVat")
    public SummeryVat[] getSummeryAllProVat() { return summeryAllProVat; }
    @JsonProperty("SummeryAllProVat")
    public void setSummeryAllProVat(SummeryVat[] value) { this.summeryAllProVat = value; }
}
