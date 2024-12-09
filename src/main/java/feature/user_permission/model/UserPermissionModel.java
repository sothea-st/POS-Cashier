package feature.user_permission.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserPermissionModel {

     private int count;
     private UserPermissionDetail[] data;

     @Setter
     @Getter
     public static class UserPermissionDetail {

          private int id;
          private String permissionName;
          private int parentId;
     }
}
