package Reporting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportingRespone {
     private int count;
     private ReportingDetailResponse[] data;

     @JsonProperty("count")
     public int getCount() {
          return count;
     }

     @JsonProperty("count")
     public void setCount(int value) {
          this.count = value;
     }

     @JsonProperty("data")
     public ReportingDetailResponse[] getData() {
          return data;
     }

     @JsonProperty("data")
     public void setData(ReportingDetailResponse[] value) {
          this.data = value;
     }
}
