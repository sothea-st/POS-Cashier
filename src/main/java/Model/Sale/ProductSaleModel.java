package Model.Sale;

public class ProductSaleModel {

     private int productId;
     private int qty;
     private double price;
     private double cost;

     private double amount;
     private double discount;
     private String discountType;

     public ProductSaleModel() {
     }

     public ProductSaleModel(int productId, int qty, double price, double amount, double discount, String discountType , double cost) {
          this.productId = productId;
          this.qty = qty;
          this.price = price;
          this.amount = amount;
          this.discount = discount;
          this.discountType = discountType;
          this.cost = cost;
     }

     public double getCost() {
          return cost;
     }

     public void setCost(double cost) {
          this.cost = cost;
     }
     
     

     public int getProductId() {
          return productId;
     }

     public void setProductId(int productId) {
          this.productId = productId;
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

     public String getDiscountType() {
          return discountType;
     }

     public void setDiscountType(String discountType) {
          this.discountType = discountType;
     }

}
