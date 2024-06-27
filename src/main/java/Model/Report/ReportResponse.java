/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Report;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOBILE-APP.02
 */
 
public class ReportResponse {

     @JsonProperty("data")
     private ReportImportDetail[] data;
     @JsonProperty("msg")
     private String msg;

     @JsonProperty("data")
     public ReportImportDetail[] getData() {
          return data;
     }

     @JsonProperty("data")
     public void setData(ReportImportDetail[] value) {
          this.data = value;
     }

     @JsonProperty("msg")
     public String getMsg() {
          return msg;
     }

     @JsonProperty("msg")
     public void setMsg(String value) {
          this.msg = value;
     }
}
