package pagination;

import Constant.JavaConnection;
import Constant.JavaRoute;
import Reporting.GroupButtonExport;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import okhttp3.Response;

public abstract class MainPaginationWithData {

     protected String pageNumber = "1";
     protected int pageSize = 10;
     protected boolean isCheckSearch = true;
     protected String searchValue;
     protected int dataCount = 0;
     protected String pageType;

     protected PaginationPanel paginationPanel;
     protected JPanel panelData;
     protected GroupButtonExport groupButtonExport;
     protected List<?> listData = new ArrayList<>();
     Response response = null;
     protected ObjectMapper objMapper = new ObjectMapper();

     public void read(boolean isCheck) {

          if (isCheck) { // get data
               response = JavaConnection.get(JavaRoute.companyProfile + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&code=Individual");
          } else { // search
               response = JavaConnection.get(JavaRoute.companyProfile + "/search?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&code=Individual&search=" + searchValue);
          }

          System.err.println("log view response : " + response);
          try {

               if (response.isSuccessful()) {
                    System.err.println("log view response :  111111111111111111");

                    String responseData = response.body().string();

//                    responseType = objMapper.readValue(responseData, classType);
//
//                    // pagination code
//                    dataCount = responseType.getCount();
//                    if (isCheck) { // true get
//                         paginationPanel.setTotalPage(dataCount, pageSize);
//                    } else { // false search
//                         paginationPanel.resetPage(dataCount);
//                    }
//
//                    listData.clear();
//                    listData = responseType.getData();
                    appendData();
               }

          } catch (Exception e) {
               System.err.println("error get individual : " + e);
          }

     }

     protected abstract void appendData();

}
