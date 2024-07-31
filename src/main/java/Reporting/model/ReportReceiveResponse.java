/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reporting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author MOBILE-APP.02
 */
public class ReportReceiveResponse {

     private long count;
     private ReportReceiveDetail[] data;

     @JsonProperty("count")
     public long getCount() {
          return count;
     }

     @JsonProperty("count")
     public void setCount(long value) {
          this.count = value;
     }

     @JsonProperty("data")
     public ReportReceiveDetail[] getData() {
          return data;
     }

     @JsonProperty("data")
     public void setData(ReportReceiveDetail[] value) {
          this.data = value;
     }
}
