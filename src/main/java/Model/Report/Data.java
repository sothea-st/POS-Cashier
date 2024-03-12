package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    private long openCashKhr;
    private String paymentNoFirst;
    private String companyLogo;
    private String companyName;
    private long totalWithdrawal;
    private SummaryVat[] summeryVat;
    private String userName;
    private DiscountSummary discountSummery;
    private String closeDate;
    private String posID;
    private long openCashUsd;
    private String paymentNoLast;
    private double cashierTotal;
    private SummarySale[] summeryPayemnt;
    private String companyAddress;
    private SummarySale[] summerySale;
    private String companyContact;
    private String openDate;
    private long closeCash;
    private SummaryVat[] summeryAllProVat;

    @JsonProperty("openCashKhr")
    public long getOpenCashKhr() { return openCashKhr; }
    @JsonProperty("openCashKhr")
    public void setOpenCashKhr(long value) { this.openCashKhr = value; }

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
    public long getTotalWithdrawal() { return totalWithdrawal; }
    @JsonProperty("totalWithdrawal")
    public void setTotalWithdrawal(long value) { this.totalWithdrawal = value; }

    @JsonProperty("SummeryVat")
    public SummaryVat[] getSummeryVat() { return summeryVat; }
    @JsonProperty("SummeryVat")
    public void setSummeryVat(SummaryVat[] value) { this.summeryVat = value; }

    @JsonProperty("userName")
    public String getUserName() { return userName; }
    @JsonProperty("userName")
    public void setUserName(String value) { this.userName = value; }

    @JsonProperty("discountSummery")
    public DiscountSummary getDiscountSummery() { return discountSummery; }
    @JsonProperty("discountSummery")
    public void setDiscountSummery(DiscountSummary value) { this.discountSummery = value; }

    @JsonProperty("closeDate")
    public String getCloseDate() { return closeDate; }
    @JsonProperty("closeDate")
    public void setCloseDate(String value) { this.closeDate = value; }

    @JsonProperty("posId")
    public String getPosID() { return posID; }
    @JsonProperty("posId")
    public void setPosID(String value) { this.posID = value; }

    @JsonProperty("openCashUsd")
    public long getOpenCashUsd() { return openCashUsd; }
    @JsonProperty("openCashUsd")
    public void setOpenCashUsd(long value) { this.openCashUsd = value; }

    @JsonProperty("paymentNoLast")
    public String getPaymentNoLast() { return paymentNoLast; }
    @JsonProperty("paymentNoLast")
    public void setPaymentNoLast(String value) { this.paymentNoLast = value; }

    @JsonProperty("cashierTotal")
    public double getCashierTotal() { return cashierTotal; }
    @JsonProperty("cashierTotal")
    public void setCashierTotal(double value) { this.cashierTotal = value; }

    @JsonProperty("summeryPayemnt")
    public SummarySale[] getSummeryPayemnt() { return summeryPayemnt; }
    @JsonProperty("summeryPayemnt")
    public void setSummeryPayemnt(SummarySale[] value) { this.summeryPayemnt = value; }

    @JsonProperty("companyAddress")
    public String getCompanyAddress() { return companyAddress; }
    @JsonProperty("companyAddress")
    public void setCompanyAddress(String value) { this.companyAddress = value; }

    @JsonProperty("SummerySale")
    public SummarySale[] getSummerySale() { return summerySale; }
    @JsonProperty("SummerySale")
    public void setSummerySale(SummarySale[] value) { this.summerySale = value; }

    @JsonProperty("companyContact")
    public String getCompanyContact() { return companyContact; }
    @JsonProperty("companyContact")
    public void setCompanyContact(String value) { this.companyContact = value; }

    @JsonProperty("openDate")
    public String getOpenDate() { return openDate; }
    @JsonProperty("openDate")
    public void setOpenDate(String value) { this.openDate = value; }

    @JsonProperty("closeCash")
    public long getCloseCash() { return closeCash; }
    @JsonProperty("closeCash")
    public void setCloseCash(long value) { this.closeCash = value; }

    @JsonProperty("SummeryAllProVat")
    public SummaryVat[] getSummeryAllProVat() { return summeryAllProVat; }
    @JsonProperty("SummeryAllProVat")
    public void setSummeryAllProVat(SummaryVat[] value) { this.summeryAllProVat = value; }
}
