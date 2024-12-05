package LoginAndLogoutForm.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleHasPermissionModel {

     private int count;
     private RoleHasPermissionDetail[] data;

     @Getter
     @Setter
     public static class RoleHasPermissionDetail {

          private int roleId;
          private String roleName;
          private int permissionId;
          private String permissionName;
          private int parentId;
          private Boolean isVisible;
          private Boolean isCreate;
          private Boolean isView;
          private Boolean isUpdate;
          private Boolean isDelete;
     }

}
