/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ProductModelV1;

import com.fasterxml.jackson.annotation.JsonProperty;

 
public class ProductResponseV1 {

     private long count;
     private ProductResponseDetailV1[] data;

     @JsonProperty("count")
     public long getCount() {
          return count;
     }

     @JsonProperty("count")
     public void setCount(long value) {
          this.count = value;
     }

     @JsonProperty("data")
     public ProductResponseDetailV1[] getData() {
          return data;
     }

     @JsonProperty("data")
     public void setData(ProductResponseDetailV1[] value) {
          this.data = value;
     }
}
