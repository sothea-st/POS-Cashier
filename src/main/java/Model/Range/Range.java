package Model.Range;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author FRONT-END.06
 */

 @Setter
 @Getter
public class Range {
    private Integer id;
    private String rangeNameEn;
    private String rangeNameKh;
    private String warehouse;
    
    public Range(){}
    
    public Range(Integer id,
            String rangeNameEn,
            String rangeNameKh,
            String warehouse
    ){
            this.id = id;
            this.rangeNameEn = rangeNameEn;
            this.rangeNameKh = rangeNameKh;
            this.warehouse = warehouse;
        
    }
    
}
