package feature.LoginAndLogoutForm;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import feature.LoginAndLogoutForm.model.RoleHasPermissionModel;
import feature.LoginAndLogoutForm.model.RoleHasPermissionModel.RoleHasPermissionDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;

public class JavaCheckPermission {

     public JavaCheckPermission(Integer roleId, LoginFormJdailog loginFormJdailog) {
          Response response = JavaConnection.get(JavaRoute.roleHasPermissions + "?roleId=" + roleId + "&parentId=0");
          
          try {
               // convert response to string 
               String responseData = response.body().string();

               // create object mapper
               ObjectMapper object = new ObjectMapper();

               // convert responseData to objectMapper
               RoleHasPermissionModel model = object.readValue(responseData, RoleHasPermissionModel.class);
                    
               
               //JavaConstant.roleHasPermissionModel = model;
               
               boolean isStock = false;
               boolean isStaff = false;
               boolean isReporting = false;
               boolean isSetting = false;

               for (RoleHasPermissionDetail data : model.getData()) {
                    
                    if (data.getPermissionName().equals("Stock")) {
                         isStock = data.getIsVisible();
                    }

                    if (data.getPermissionName().equals("Settings")) {
                         isSetting = data.getIsVisible();
                    }

                    if (data.getPermissionName().equals("Staff")) {
                         isStaff = data.getIsVisible();
                    }

                    if (data.getPermissionName().equals("Reporting")) {
                         isReporting = data.getIsVisible();
                    }
               }

               loginFormJdailog.checkPermission(isStock, isStaff, isReporting, isSetting);

          } catch (Exception e) {
               System.err.println("error :" + e);
          }

     }

}
