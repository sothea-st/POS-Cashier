package Model.Uom;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UomModel {
    private Integer id;
    private String uomNameEn;
    private String uomNameKh;
    
    public UomModel(){}
    
    public UomModel(
            Integer id,
            String uomNameEn,
            String uomNameKh
    ){
        this.id = id;
        this.uomNameEn = uomNameEn;
        this.uomNameKh = uomNameKh;
    }
}
