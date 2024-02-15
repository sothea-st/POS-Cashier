
package Model.HoldOrder;

import java.awt.Component;


public class NewHoldOrderModel {
     private int qty;
     private Component[] listCom;
     private int number;
 
     public NewHoldOrderModel() {
     }

     public NewHoldOrderModel(int number, int qty, Component[] listCom) {
          this.number = number;
          this.qty = qty;
          this.listCom = listCom;
     }

     public int getQty() {
          return qty;
     }

     public void setQty(int qty) {
          this.qty = qty;
     }

     public Component[] getListCom() {
          return listCom;
     }

     public void setListCom(Component[] listCom) {
          this.listCom = listCom;
     }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
