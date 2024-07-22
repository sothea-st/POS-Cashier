package Model.Brand;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetailBrandModel {
    private Integer id;
    private String brandNameEn;
    private String brandNameKh;
    private Integer createBy;
    private String createDate;
    private Boolean status;
    private Boolean isDeleted;
}
