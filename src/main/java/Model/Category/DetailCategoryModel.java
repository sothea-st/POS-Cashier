package Model.Category;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DetailCategoryModel {
    private Integer id;
    private String catNameKh;
    private String catNameEn;
    private Integer parentId;
    private Integer movePosition;
    private String code;
    private boolean status;
    private String createDate;
    private Integer createBy;
    private boolean deleted;
}
