package Model.Login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginModel {

     private String msg;
     private Integer empID;
     private String posID;
     private Integer roleID;
     private String roleName;
     private Integer id;
     private String userName;
     private String userCode;
     private String token;

     @JsonProperty("empId")
     public Integer getEmpID() {
          return empID;
     }

     @JsonProperty("empId")
     public void setEmpID(Integer value) {
          this.empID = value;
     }
     
     
     @JsonProperty("msg")
     public String getMag() {
          return msg;
     }

     @JsonProperty("msg")
     public void setMsg(String value) {
          this.msg = value;
     }
     

     @JsonProperty("posId")
     public String getPosID() {
          return posID;
     }

     @JsonProperty("posId")
     public void setPosID(String value) {
          this.posID = value;
     }

     @JsonProperty("roleId")
     public Integer getRoleID() {
          return roleID;
     }

     @JsonProperty("roleId")
     public void setRoleID(Integer value) {
          this.roleID = value;
     }

     @JsonProperty("roleName")
     public String getRoleName() {
          return roleName;
     }

     @JsonProperty("roleName")
     public void setRoleName(String value) {
          this.roleName = value;
     }

     @JsonProperty("id")
     public Integer getID() {
          return id;
     }

     @JsonProperty("id")
     public void setID(Integer value) {
          this.id = value;
     }

     @JsonProperty("userName")
     public String getUserName() {
          return userName;
     }

     @JsonProperty("userName")
     public void setUserName(String value) {
          this.userName = value;
     }

     @JsonProperty("userCode")
     public String getUserCode() {
          return userCode;
     }

     @JsonProperty("userCode")
     public void setUserCode(String value) {
          this.userCode = value;
     }

     @JsonProperty("token")
     public String getToken() {
          return token;
     }

     @JsonProperty("token")
     public void setToken(String value) {
          this.token = value;
     }
}
