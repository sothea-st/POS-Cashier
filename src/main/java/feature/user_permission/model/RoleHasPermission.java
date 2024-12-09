 
package feature.user_permission.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
