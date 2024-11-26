package Model.Range;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author FRONT-END.06
 */

@Setter
@Getter
public class RangeSelectModel {
    private String rangeNameEn;
    private Integer id;
    
    public RangeSelectModel(){}
    
    public RangeSelectModel(Integer id,
            String rangeNameEn
    ){
            this.id = id;
            this.rangeNameEn = rangeNameEn;
        
    }
}
