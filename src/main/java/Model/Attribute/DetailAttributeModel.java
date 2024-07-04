package Model.Attribute;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetailAttributeModel {
    private Integer id;
    private String attrNameEn;
    private String attrNameKh;
    private Boolean status;
    private Boolean isDeleted;
}
