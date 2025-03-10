package feature.order_online.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderOnlineCalculateResponse {

     private int status;
     private String msg;
     private Calculate data;

     @Setter
     @Getter
     public static class Calculate {

          private OrderStatus newOrders;
          private OrderStatus cancelled;
          private OrderStatus completed;
     }

     @Setter
     @Getter
     public static class OrderStatus {

          private int count;
          private BigDecimal total;
     }
}
