package Model.Staff;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DetailGetDataModel {
    private int id;
    private String nameKh;
    private String nameEn;
    private String gender;
    private String dob;
    private String startDate;
    private String imageName;
    private String contact;
    private String address;
    private int createBy;
    private String createDate;
    private boolean status;
    private boolean deleted;
    private Integer roleId;
}
