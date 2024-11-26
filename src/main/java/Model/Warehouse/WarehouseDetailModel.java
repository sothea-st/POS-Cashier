package Model.Warehouse;

import Model.Warehouse.WarehouseModel.WarehouseDetail;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author FRONT-END.06
 */

@Setter
@Getter
public class WarehouseDetailModel {
    
    private int status;
    private String msg;
    private WarehouseDetail data;
    
    public static WarehouseDetail detail;
}
