 
package Model.ReturnModel;
 
import java.math.BigDecimal;

public class ReturnProductModel {
    private int proId;
    private int qty;
    private double price;
    private double amount;
    private double discount;

    public ReturnProductModel() {
    }

    public ReturnProductModel(int proId, int qty, double price, double amount,double discount) {
        this.proId = proId;
        this.qty = qty;
        this.price = price;
        this.amount = amount;
        this.discount = discount;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
    
    
}
