/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feature.HoldOrder;

import Model.ProductModel.ProductDataModel;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author MOBILE-APP.02
 */
public class HoldDataSuccessModel {

     private String note;
     private long qtyHold;
     private long holdID;
     private HoldDetail[] details;

     @JsonProperty("note")
     public String getNote() {
          return note;
     }

     @JsonProperty("note")
     public void setNote(String value) {
          this.note = value;
     }

     @JsonProperty("qty_hold")
     public long getQtyHold() {
          return qtyHold;
     }

     @JsonProperty("qty_hold")
     public void setQtyHold(long value) {
          this.qtyHold = value;
     }

     @JsonProperty("hold_id")
     public long getHoldID() {
          return holdID;
     }

     @JsonProperty("hold_id")
     public void setHoldID(long value) {
          this.holdID = value;
     }

     @JsonProperty("details")
     public HoldDetail[] getDetails() {
          return details;
     }

     @JsonProperty("details")
     public void setDetails(HoldDetail[] value) {
          this.details = value;
     }
}
