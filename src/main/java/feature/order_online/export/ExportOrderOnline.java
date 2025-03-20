package feature.order_online.export;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.order_online.model.OrderOnlineModelResponse;
import java.util.List;
import okhttp3.Response;

public class ExportOrderOnline {

     public static void read(List<Object[]> dataList, String dateFrom, String dateTo) {

          Response response = JavaConnection.get(JavaRoute.orderOnline + "?dateFrom=" + dateFrom + "&dateTo=" + dateTo);

          System.err.println("resposne : " + response);

          try {

               if (response.isSuccessful()) {

                    String responseData = response.body().string();

                    ObjectMapper objMapper = new ObjectMapper();

                    OrderOnlineModelResponse data = objMapper.readValue(responseData, OrderOnlineModelResponse.class);

                    int i = 0;

                    for (OrderOnlineModelResponse.OrderOnlineData detail : data.getData()) {
                         i++;
                         dataList.add(new Object[]{
                              String.valueOf(i),
                              JavaConstant.formateDateDDMMYYYY(detail.getOrderDate()),
                              detail.getOrderNumber(),
                              detail.getOrderStatus(),
                              detail.getCustomerId(),
                              detail.getCustomerName(),
                              JavaConstant.nullValueOrEmpty(detail.getPhoneNumber()),
                              detail.getDeliveryInformation(),
                              JavaConstant.setAmount(detail.getTotalAmount()),
                              detail.getPaymentMethod(),
                              detail.getPaymentStatus(),
                              JavaConstant.nullValueOrEmpty(detail.getDeliveryAddress()),
                              detail.getCustomerNote()});
                    }

               }

          } catch (Exception e) {
               System.err.println("error get online eeeeeeeeeeeeeeeeeeee : " + e);
          }
     }
}
