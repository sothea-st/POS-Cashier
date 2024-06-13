package Model.Role;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class RoleModel {
    public RoleModel(){}
    
    public RoleModel(int roleId, String roleName){
        this.roleId = roleId;
        this.roleName = roleName;
    }
    
     private int roleId;
     private String roleName;
}
