package Model.HoldOrder;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.awt.Component;

public class DataHoldOrder {
    private int id;
    private String note;
    private int qtyHold;
    private Component[] listCom;

    @JsonProperty("id")
    public int getID() { return id; }
    @JsonProperty("id")
    public void setID(int value) { this.id = value; }

    @JsonProperty("note")
    public String getNote() { return note; }
    @JsonProperty("note")
    public void setNote(String value) { this.note = value; }

    @JsonProperty("qty_hold")
    public int getQtyHold() { return qtyHold; }
    @JsonProperty("qty_hold")
    public void setQtyHold(int value) { this.qtyHold = value; }
    
//    JsonProperty("listCom")
//    public Component[] listCom { return listCom; }
//    @JsonProperty("listCom")
//    public void setListCom(Component[] value) { this.listCom = value; }
    
    public Component[] getListCom() {
          return listCom;
     }

     public void setListCom(Component[] listCom) {
          this.listCom = listCom;
     }
}
