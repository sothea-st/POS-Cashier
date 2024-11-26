package Model.Slot;

import Model.Slot.SlotModel.SlotDetail;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author FRONT-END.06
 */

@Setter
@Getter
public class SlotDetailModel {
    private int status;
    private String msg;
    private SlotDetail data;
    
    public static SlotDetail detail;
}
