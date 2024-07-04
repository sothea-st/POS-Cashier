package Model.Attribute;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ListAttributeModel {
    private Integer count;
    private DataAttributeModel[] content;
}
