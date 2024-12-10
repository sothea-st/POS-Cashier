package feature.user_permission.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissionModel {

     private int status;
     private String msg;
     private PermissionDetail data;

     @Setter
     @Getter
     public static class PermissionDetail {

          private int roleId;
          private String roleName;
          private int permissionId;
          private String permissionName;
          private int parentId;
          private Boolean isVisible; // Updated
          private Boolean isCreate;  // Updated
          private Boolean isView;    // Updated
          private Boolean isUpdate;  // Updated
          private Boolean isDelete;  // Updated
     }

}
