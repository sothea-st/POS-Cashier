package Model.Uom;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DetailUomModel {

    private int status;
    private String msg;
    private UomDetail data;
    private int count;
    
    @Setter
    @Getter
    public static class UomDetail{
        private int id;
        private String uomNameEn;
        private String uomNameKh;
    }
    
    
}
