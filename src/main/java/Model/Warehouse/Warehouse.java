package Model.Warehouse;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author FRONT-END.06
 */

@Setter
@Getter
public class Warehouse {
    private String warehouseNameEn;
    private String warehouseNameKh;
    private Integer id;
    
    public Warehouse(){}
    
    public Warehouse(Integer id,
            String warehouseNameEn,
            String warehouseNameKh
    ){
            this.id = id;
            this.warehouseNameEn = warehouseNameEn;
            this.warehouseNameKh = warehouseNameKh;
        
    }
}
