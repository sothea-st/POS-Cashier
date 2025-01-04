package feature.HoldOrder.HoldModelDir;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataListHold {

     private int id;
     private String note;
     private int qtyHold;
     private ListDetailHold[] listDetails;

     @JsonProperty("id")
     public int getID() {
          return id;
     }

     @JsonProperty("id")
     public void setID(int value) {
          this.id = value;
     }

     @JsonProperty("note")
     public String getNote() {
          return note;
     }

     @JsonProperty("note")
     public void setNote(String value) {
          this.note = value;
     }

     @JsonProperty("qtyHold")
     public int getQtyHold() {
          return qtyHold;
     }

     @JsonProperty("qtyHold")
     public void setQtyHold(int value) {
          this.qtyHold = value;
     }

     @JsonProperty("listDetails")
     public ListDetailHold[] getListDetails() {
          return listDetails;
     }

     @JsonProperty("listDetails")
     public void setListDetails(ListDetailHold[] value) {
          this.listDetails = value;
     }
}
