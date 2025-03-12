package feature.report.report_product_stock_in.export;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.report.report_product_stock_in.model.ReportProductStockInResponse;
import java.util.List;
import okhttp3.Response;

public class ExportProductStockIn {

     public static void read(List<Object[]> dataList, String dateFrom, String dateTo) {

          Response response = JavaConnection.get(JavaRoute.reportProductStockIn + "?dateFrom=" + dateFrom + "&dateTo=" + dateTo);

          try {

               String responesData = response.body().string();

               ObjectMapper objMapper = new ObjectMapper();

               ReportProductStockInResponse data = objMapper.readValue(responesData, ReportProductStockInResponse.class);

               Integer index = 0;

               for (ReportProductStockInResponse.ReportProductStockInDetailResponse detail : data.getData()) {
                    index++;
                    dataList.add(new Object[]{
                         String.valueOf(index),
                         detail.getProductName(),
                         detail.getCategoryName(),
                         JavaConstant.nullValueOrEmpty(detail.getSupplierName()),
                         JavaConstant.setAmount(detail.getPrice()),
                         detail.getQty(),
                         JavaConstant.formateDateDDMMYYYY(detail.getDate()),
                         JavaConstant.nullValueOrEmpty(detail.getDescription())
                    });
               }

          } catch (Exception e) {
               System.err.println("error get report vendor : " + e);
          }

     }
}
