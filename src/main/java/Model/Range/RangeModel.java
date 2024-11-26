package Model.Range;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author FRONT-END.06
 */

@Setter
@Getter
public class RangeModel {
    
    private Integer count;
    private RangeDetail[] data;
    
    @Setter
    @Getter
    public static class RangeDetail {
        private Integer id;
        private String rangeNameEn;
        private String rangeNameKh;
        private Warehouse warehouse;
    }
    
    @Setter
    @Getter
    public static class Warehouse {
        private Integer id;
        private String warehouseNameEn;
        private String warehouseNameKh;
    }
    
}
