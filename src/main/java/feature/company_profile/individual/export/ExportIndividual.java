package feature.company_profile.individual.export;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.company_profile.individual.model.IndividualModel;
import java.math.BigDecimal;
import java.util.List;
import okhttp3.Response;

public class ExportIndividual {

     public static void read(List<Object[]> dataList) {

          Response response = JavaConnection.get(JavaRoute.companyProfile + "?code=Individual");

          try {

               if (response.isSuccessful()) {

                    String responseData = response.body().string();

                    ObjectMapper objMapper = new ObjectMapper();

                    IndividualModel data = objMapper.readValue(responseData, IndividualModel.class);

                    for (IndividualModel.IndividualDetail detail : data.getData()) {
                         dataList.add(new Object[]{
                              detail.getCustomerId(),
                              detail.getGender(),
                              detail.getNationality(),
                              detail.getLastName() + " " + detail.getFirstName(),
                              "N/A",
                              JavaConstant.formatPhoneNumber(detail.getPhoneNumber()),
                              detail.getEmail(),
                              JavaConstant.setAmount(BigDecimal.valueOf(0.00)),
                              detail.getCreatedDate()});
                    }

               }

          } catch (Exception e) {
               System.err.println("error get individual : " + e);
          }
     }
}
