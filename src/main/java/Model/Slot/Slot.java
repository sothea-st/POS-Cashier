package Model.Slot;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author FRONT-END.06
 */

@Setter
@Getter
public class Slot {
    
    private Integer id;
    private String slotNameEn;
    private String slotNameKh;
    private String range;
    
    public Slot(){}
    
    public Slot(Integer id,
            String slotNameEn,
            String slotNameKh,
            String range
    ){
            this.id = id;
            this.slotNameEn = slotNameEn;
            this.slotNameKh = slotNameKh;
            this.range = range;
        
    }
}
