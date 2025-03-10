package feature.order_online.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import pagination.PaginationData;

@Setter
@Getter
public class OrderOnlineModelResponse implements PaginationData {

     private int count;
     private List<OrderOnlineData> data;

     @Setter
     @Getter
     public static class OrderOnlineData {

          private int id;
          private String reason;
          private String orderDate;
          private String orderNumber;
          private String orderStatus;
          private String customerId;
          private String customerName;
          private String phoneNumber;
          private String deliveryInformation;
          private BigDecimal totalAmount;
          private String paymentMethod;
          private String paymentStatus;
          private String deliveryAddress;
          private String customerNote;
          private String subTotal;
          private String discount;
          private BigDecimal deliveryFee;
          private String grandTotal;
          private List<OrderOnlineDetailResponse> details;
          private String countAndSum;
     }

     @Setter
     @Getter
     public static class OrderOnlineDetailResponse {

          private String barcode;
          private String englishName;
          private String khmerName;
          private int qty;
          private BigDecimal salePrice;
          private String discountType;
          private String discountPrice;
          private BigDecimal total;
     }
}
