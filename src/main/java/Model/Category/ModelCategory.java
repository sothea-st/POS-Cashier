package Model.Category;

import lombok.Getter;
import lombok.Setter;

 

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
            this.categoryNameEn = categoryNameKh;
            this.categoryNameKh =categoryNameEn;
            this.movePosition = movePosition;
            this.parentId = parentId;
        
    }

     public String getCategoryNameEn() {
          return categoryNameEn;
     }

     public void setCategoryNameEn(String categoryNameEn) {
          this.categoryNameEn = categoryNameEn;
     }

     public String getCategoryNameKh() {
          return categoryNameKh;
     }

     public void setCategoryNameKh(String categoryNameKh) {
          this.categoryNameKh = categoryNameKh;
     }

     public Integer getId() {
          return id;
     }

     public void setId(Integer id) {
          this.id = id;
     }

     public Integer getMovePosition() {
          return movePosition;
     }

     public void setMovePosition(Integer movePosition) {
          this.movePosition = movePosition;
     }

     public Integer getParentId() {
          return parentId;
     }

     public void setParentId(Integer parentId) {
          this.parentId = parentId;
     }
    
    
    
    
    
}
