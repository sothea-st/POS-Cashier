/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlogCode;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author mobile-app.02
 */
public class ReturnProductByBarcode {

     private String msg;
     private ParentProductModel[] data;

     @JsonProperty("msg")
     public String getMsg() {
          return msg;
     }

     @JsonProperty("msg")
     public void setMsg(String value) {
          this.msg = value;
     }

     @JsonProperty("data")
     public ParentProductModel[] getData() {
          return data;
     }

     @JsonProperty("data")
     public void setData(ParentProductModel[] value) {
          this.data = value;
     }
}
