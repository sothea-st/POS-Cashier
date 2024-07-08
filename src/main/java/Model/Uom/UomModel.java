package Model.Uom;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UomModel {
    private String nameEn;
    private String nameKh;
    private Integer id;
    
    public UomModel(){}
    
    public UomModel(
            Integer id,
            String nameEn,
            String nameKh
    ){
        this.id = id;
        this.nameEn = nameEn;
        this.nameKh = nameKh;
    }
}
