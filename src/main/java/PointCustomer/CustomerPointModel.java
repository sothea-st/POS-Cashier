package PointCustomer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerPointModel {

     private Double totalAmountEarned;
     private int pointEarned;
     private String customerID;
     private String contact;

     @JsonProperty("total_amount_earned")
     public Double getTotalAmountEarned() {
          return totalAmountEarned;
     }

     @JsonProperty("total_amount_earned")
     public void setTotalAmountEarned(Double value) {
          this.totalAmountEarned = value;
     }

     @JsonProperty("point_earned")
     public int getPointEarned() {
          return pointEarned;
     }

     @JsonProperty("point_earned")
     public void setPointEarned(int value) {
          this.pointEarned = value;
     }

     @JsonProperty("customer_id")
     public String getCustomerID() {
          return customerID;
     }

     @JsonProperty("customer_id")
     public void setCustomerID(String value) {
          this.customerID = value;
     }

     @JsonProperty("contact")
     public String getContact() {
          return contact;
     }

     @JsonProperty("contact")
     public void setContact(String value) {
          this.contact = value;
     }
}
