package Model.Warehouse;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author FRONT-END.06
 */

@Setter
@Getter
public class WarehouseSelectModel {
    private String warehouseNameEn;
    private Integer id;
    
    public WarehouseSelectModel(){}
    
    public WarehouseSelectModel(Integer id,
            String warehouseNameEn
    ){
            this.id = id;
            this.warehouseNameEn = warehouseNameEn;
        
    }
}
