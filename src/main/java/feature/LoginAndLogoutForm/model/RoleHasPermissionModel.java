package feature.LoginAndLogoutForm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RoleHasPermissionModel {

     private int count;
     private RoleHasPermissionDetail[] data;

     @Getter
     @Setter
     @Builder
     @AllArgsConstructor
     @NoArgsConstructor
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
