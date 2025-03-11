package feature.report.report_vendor.export;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.report.report_vendor.model.ReportVendorResponse;
import java.util.List;
import okhttp3.Response;

public class ExportReportVendor {

     public static void read(List<Object[]> dataList,String dateFrom, String dateTo) {

          Response response = JavaConnection.get(JavaRoute.vendor + "/report?dateFrom=" + dateFrom + "&dateTo=" + dateTo);

          try {

               String responesData = response.body().string();

               ObjectMapper objMapper = new ObjectMapper();

               ReportVendorResponse data = objMapper.readValue(responesData, ReportVendorResponse.class);

               Integer index = 0;

               for (ReportVendorResponse.ReportVendorResponseDetail detail : data.getData()) {
                    index++;
                    dataList.add(new Object[]{
                         String.valueOf(index),
                         detail.getVendorName(),
                         JavaConstant.formatPhoneNumber(detail.getContact()),
                         JavaConstant.nullValueOrEmpty(detail.getEmail()),
                         JavaConstant.nullValueOrEmpty(detail.getWebsite()),
                         JavaConstant.nullValueOrEmpty(detail.getAddress())
                    });
               }

          } catch (Exception e) {
               System.err.println("error get report vendor : " + e);
          }

     }
}
