package Model.Uom;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter 

public class DataUomModel {
    private Integer id;
    private String nameEn;
    private String nameKh;
    private Boolean status;
    private Boolean isDeleted;
}
