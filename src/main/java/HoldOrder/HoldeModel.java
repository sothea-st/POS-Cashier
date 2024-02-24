package HoldOrder;

public class HoldeModel {

     private int id;
     private int qtyHold;

     public HoldeModel() {
     }

     public HoldeModel(int id, int qtyHold) {
          this.id = id;
          this.qtyHold = qtyHold;
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

     
 

}
