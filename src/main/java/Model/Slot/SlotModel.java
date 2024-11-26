package Model.Slot;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author FRONT-END.06
 */

@Setter
@Getter
public class SlotModel {
    private Integer count;
    private SlotDetail[] data;
    
    @Setter
    @Getter
    public static class SlotDetail {
        private Integer id;
        private String slotNameEn;
        private String slotNameKh;
        private Range range;
    }
    
    @Setter
    @Getter
    public static class Range {
        private Integer id;
        private String rangeNameEn;
        private String rangeNameKh;
    }
    
}
