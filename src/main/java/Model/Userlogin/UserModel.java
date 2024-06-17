
package Model.Userlogin;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserModel {
     private int id;
     private int emId;
     private String userName;
     private String userCode;
     
     public UserModel(){}
     
     public UserModel(int id,
             int emId,
             String userName,
             String userCode
     ){
         this.id = id;
         this.emId = emId;
         this.userName = userName;
         this.userCode = userCode;
     }
     
}
