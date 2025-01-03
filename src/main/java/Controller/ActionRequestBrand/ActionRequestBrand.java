package Controller.ActionRequestBrand;

import Components.ComboBox;
import Constant.JavaConnection;
import Constant.JavaRoute;
import Components.Event.ButtonEvent;
import Model.Brand.BrandModel;
import Model.Brand.BrandSuccessModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import okhttp3.Response;

public class ActionRequestBrand {

     public static void requestBrand(ComboBox cmBox) {
      
          Response response = JavaConnection.get(JavaRoute.brand);
          HashMap<String, String> map = new HashMap<>();
          try {
              
               if( cmBox.getItemCount() > 1 ) {
                    return;
               }
               
               if (response.isSuccessful()) {   
                   
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    BrandSuccessModel data = objMap.readValue(responseData, BrandSuccessModel.class);
                    BrandModel[] listBrand = data.getData();
                    
                    for (int i = 0; i < listBrand.length; i++) {
                         map.put(listBrand[i].getBrandNameEn(), "" + listBrand[i].getId());
                    }
                 
                    cmBox.setMap(map);
               }
          } catch (Exception e) {
               System.err.println("error get brand = " + e);
          }
     }
}
