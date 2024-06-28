/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepostSaleResponse {

     private String msg;
     private ReportSaleDetail[] data;

     @JsonProperty("msg")
     public String getMsg() {
          return msg;
     }

     @JsonProperty("msg")
     public void setMsg(String value) {
          this.msg = value;
     }

     @JsonProperty("data")
     public ReportSaleDetail[] getData() {
          return data;
     }

     @JsonProperty("data")
     public void setData(ReportSaleDetail[] value) {
          this.data = value;
     }
}
