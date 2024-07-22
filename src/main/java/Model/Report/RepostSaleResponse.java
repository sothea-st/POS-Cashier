/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepostSaleResponse {

     private int count;
     private ReportSaleDetail[] data;

     @JsonProperty("count")
     public int getCount() {
          return count;
     }

     @JsonProperty("count")
     public void setCount(int value) {
          this.count = value;
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
