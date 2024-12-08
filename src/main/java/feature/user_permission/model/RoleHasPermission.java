 
package feature.user_permission.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleHasPermission {
    private Integer roleId;
    private Integer permissionId;
    private Integer parentId;
    private Boolean isVisible;
    private Boolean isCreate;
    private Boolean isView;
    private Boolean isUpdate;
    private Boolean isDelete;
}
