package Model.Attribute;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DataAttributeModel {
    private Integer id;
    private String attrNameEn;
    private String attrNameKh;
    private Boolean status;
    private Boolean isDeleted;
}
