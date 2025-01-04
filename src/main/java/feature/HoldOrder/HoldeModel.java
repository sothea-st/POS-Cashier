package feature.HoldOrder;

public class HoldeModel {

     private int id;
     private int qtyHold;
     private String discountType;
     private double discount;

     public HoldeModel() {
     }

     public HoldeModel(int id, int qtyHold,String discountType,double discount) {
          this.id = id;
          this.qtyHold = qtyHold;
          this.discountType = discountType;
          this.discount = discount;
     }

     public HoldeModel(int id) {
          this.id = id;
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public int getQtyHold() {
          return qtyHold;
     }

     public void setQtyHold(int qtyHold) {
          this.qtyHold = qtyHold;
     }

     public String getDiscountType() {
          return discountType;
     }

     public void setDiscountType(String discountType) {
          this.discountType = discountType;
     }

     public double getDiscount() {
          return discount;
     }

     public void setDiscount(double discount) {
          this.discount = discount;
     }

     
 

}
