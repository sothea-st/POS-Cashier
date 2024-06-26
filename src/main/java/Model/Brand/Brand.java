package Model.Brand;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Brand {
    private String brandNameEn;
    private String brandNameKh;
    private Integer id;
    
    public Brand(){}
    
    public Brand(Integer id,
            String brandNameEn,
            String brandNameKh
    ){
            this.id = id;
            this.brandNameEn = brandNameEn;
            this.brandNameKh = brandNameKh;
        
    }
}
