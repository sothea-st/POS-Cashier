package Model.Attribute;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Attribute {
    private String attributeNameEn;
    private String attributeNameKh;
    private Integer id;
    
    public Attribute(){}
    
    public Attribute(
            Integer id,
            String attributeNameEn,
            String attributeNameKh
    ){
        this.id = id;
        this.attributeNameEn = attributeNameEn;
        this.attributeNameKh = attributeNameKh;
    }
}
