package Model.Brand;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BrandModel {

     private int id;
     private String brandNameEn;
     private String brandNameKh;
     private int createBy;
     private String createDate;
     private Boolean status;
     private Boolean isDeleted;

}
