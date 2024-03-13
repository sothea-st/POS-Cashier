 
package UpdateQty;
 
public class UpdateQtyModel {
     private int proId;
     private int qty;
     private String sign;
     
     public UpdateQtyModel(){
     
     }

     public UpdateQtyModel(int proId, int qty, String sign) {
          this.proId = proId;
          this.qty = qty;
          this.sign = sign;
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

     public String getSign() {
          return sign;
     }

     public void setSign(String sign) {
          this.sign = sign;
     }
     
     
     
     
     
}
