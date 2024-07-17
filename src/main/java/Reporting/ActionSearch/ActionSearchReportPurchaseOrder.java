package Reporting.ActionSearch;

import Constant.JavaConnection;
import Constant.JavaRoute;
import Reporting.ReportingPurchaseOrder;
import Reporting.model.ReportingDetailResponse;
import Reporting.model.ReportingRespone;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;
import okhttp3.Response;

public class ActionSearchReportPurchaseOrder {

     public static void search(String value, JPanel panelItem, ReportingPurchaseOrder re ,ArrayList<ReportingDetailResponse> listDetail) {
          Response response = JavaConnection.get(JavaRoute.filterReportPurchaseOrder + "/" + value);
          try {
               String stringData = response.body().string();
               ObjectMapper objectMapper = new ObjectMapper();
               ReportingRespone data = objectMapper.readValue(stringData, ReportingRespone.class);
               ReportingDetailResponse[] lists = data.getData();
               panelItem.removeAll();
               panelItem.repaint();
               panelItem.revalidate();
               listDetail.clear();
               listDetail.addAll(Arrays.asList(lists));
               re.appendPurchaeOrder(lists);
          } catch (Exception e) {
               System.out.println("error filter : " + e);
          }
     }
}
