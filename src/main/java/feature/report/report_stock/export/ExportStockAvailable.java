 
package feature.report.report_stock.export;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.report.report_stock.model.StockAvailableResponse;
import feature.report.report_stock.model.StockAvailableResponse.StockAvailableDetail;
import java.util.List;
import okhttp3.Response;

 
public class ExportStockAvailable {
     
     public static void read(List<Object[]> dataList,Integer statusId) {

          Response response = JavaConnection.get(JavaRoute.reportStock+"/stockAvailable?statusId="+statusId);
               
          try {

               if (response.isSuccessful()) {

                    String responseData = response.body().string();

                    ObjectMapper objMapper = new ObjectMapper();

                    StockAvailableResponse data = objMapper.readValue(responseData, StockAvailableResponse.class);

                    Integer ind = 0;
                    
                    for (StockAvailableDetail detail : data.getData()) {
                         ind++;
                         dataList.add(new Object[]{
                              String.valueOf(ind),
                              detail.getProductName(),
                              detail.getCategoryName(),
                              JavaConstant.setAmount(detail.getCost()),
                              JavaConstant.setAmount(detail.getPrice()),
                              String.valueOf(detail.getQty())
                             });
                    }

               }

          } catch (Exception e) {
               System.err.println("error get individual : " + e);
          }
     }
}
