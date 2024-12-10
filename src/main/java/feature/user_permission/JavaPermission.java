package feature.user_permission;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import LoginAndLogoutForm.model.RoleHasPermissionModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.user_permission.model.PermissionModel;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Response;

public class JavaPermission {

     public static PermissionModel.PermissionDetail getPermissionDetail(Integer permissionId) {

          Response response = JavaConnection.get(JavaRoute.roleHasPermissions + "/readByPermissionAndRole?roleId=" + JavaConstant.roleId + "&permissionId=" + permissionId);
          
         // System.err.println("respnse permission : " + response);
          
          PermissionModel.PermissionDetail permissionDetail = new PermissionModel.PermissionDetail();

          try {
               // convert response to string 
               String responseData = response.body().string();

               // create object mapper
               ObjectMapper object = new ObjectMapper();

               // convert responseData to objectMapper
               PermissionModel model = object.readValue(responseData, PermissionModel.class);

               permissionDetail = model.getData();
               
               //System.err.println("permission name ; " + permissionDetail.getPermissionName());

          } catch (Exception e) {
               System.err.println("error :" + e);
          }

          return permissionDetail;

     }
     
     
     public static List<RoleHasPermissionModel.RoleHasPermissionDetail> getPermissions(Integer parentId) {
          // parentId from table pos_permission
          Response response = JavaConnection.get(JavaRoute.roleHasPermissions + "?roleId=" + JavaConstant.roleId + "&parentId=" + parentId);

          List<RoleHasPermissionModel.RoleHasPermissionDetail> list = new ArrayList<>();
          try {
               // convert response to string 
               String responseData = response.body().string();

               // create object mapper
               ObjectMapper object = new ObjectMapper();

               // convert responseData to objectMapper
               RoleHasPermissionModel model = object.readValue(responseData, RoleHasPermissionModel.class);
     
               for (RoleHasPermissionModel.RoleHasPermissionDetail detail : model.getData()) {
                    list.add(RoleHasPermissionModel.RoleHasPermissionDetail.builder()
                         .roleId(JavaConstant.roleId)
                         .roleName(detail.getRoleName())
                         .permissionId(detail.getPermissionId())
                         .permissionName(detail.getPermissionName())
                         .parentId(detail.getParentId())
                         .isVisible(detail.getIsVisible())
                         .isCreate(detail.getIsCreate())
                         .isUpdate(detail.getIsUpdate())
                         .isView(detail.getIsView())
                         .isDelete(detail.getIsDelete())
                         .build());
               }

          } catch (Exception e) {
               System.err.println("error :" + e);
          }

          return list;
     }
}
