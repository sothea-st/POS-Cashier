package Model.Staff;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StaffModel {
     private int id;
     private String nameKh;
     private String nameEn;
     private String gender;
     private String dob;
     private String startDate;
     private String imageName;
     private String contact;
     private String address;
     private String roleName;
     private int createBy;
     private String createDate;
     private boolean status;
     private boolean deleted;
     
    public StaffModel(){}
    
     public StaffModel( int id,
             String nameEn,
             String nameKh,
             String gender,
             String dob,
             String startDate,
             String imageName,
             String contact,
             String address,
             String roleName,
             int createBy,
             String createDate,
             boolean status,
             boolean deleted
     ){
            this.id = id;
            this.nameEn = nameEn;
            this.nameKh = nameKh;
            this.gender = gender;
            this.dob = dob;
            this.startDate = startDate;
            this.imageName = imageName;
            this.contact = contact;
            this.address = address;
            this.roleName = roleName;
            this.createBy = createBy;
            this.createDate = createDate;
            this.status = status;
            this.deleted = deleted;
            
     }

     
}
