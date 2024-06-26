package Model.Category;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryGetdataModel {
    private Integer id;
    private String catNameKh;
    private String catNameEn;
    private Integer parentId;
    private Integer movePosition;
    private Boolean status;
    private String createDate;
    private Integer createBy;
    private Boolean deleted;
}
