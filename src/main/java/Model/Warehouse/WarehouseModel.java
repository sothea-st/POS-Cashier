package Model.Warehouse;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author FRONT-END.06
 */
@Setter
@Getter
public class WarehouseModel {
    
    private Integer count;
    private WarehouseDetail[] data;
    
    @Setter
    @Getter
    public static class WarehouseDetail{
        private int id;
        private String warehouseNameEn;
        private String warehouseNameKh;
    }
    
}
