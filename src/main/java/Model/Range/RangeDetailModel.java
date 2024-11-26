package Model.Range;

import Model.Range.RangeModel.RangeDetail;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author FRONT-END.06
 */

@Setter
@Getter
public class RangeDetailModel {
    
    private int status;
    private String msg;
    private RangeDetail data;
    
    public static RangeDetail detail;
}
