package Stock.PurchaseOrderCheck;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailsByModel {
     private String date;
     private String name;

     @JsonProperty("date")
     public String getDate() {
          return date;
     }

     @JsonProperty("date")
     public void setDate(String value) {
          this.date = value;
     }

     @JsonProperty("name")
     public String getName() {
          return name;
     }

     @JsonProperty("name")
     public void setName(String value) {
          this.name = value;
     }
}
