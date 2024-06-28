package Model.Category;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ModelCategory {
    private String categoryNameEn;
    private String categoryNameKh;
    private Integer id;
    private Integer movePosition;
    private Integer parentId;
    
    public ModelCategory(){}
    
    public ModelCategory(Integer id,
            String categoryNameKh,
            String categoryNameEn,
            Integer movePosition,
            Integer parentId
    ){
            this.id = id;
            this.categoryNameEn = categoryNameEn;
            this.categoryNameKh = categoryNameKh;
            this.movePosition = movePosition;
            this.parentId = parentId;
        
    }
}
