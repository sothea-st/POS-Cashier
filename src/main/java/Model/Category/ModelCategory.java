package Model.Category;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ModelCategory {
    private String categoryNameEn;
    private String categoryNameKh;
    private Integer id;
    
    public ModelCategory(){}
    
    public ModelCategory(Integer id,
            String categoryNameKh,
            String categoryNameEn
    ){
            this.id = id;
            this.categoryNameEn = categoryNameEn;
            this.categoryNameKh = categoryNameKh;
        
    }
}
