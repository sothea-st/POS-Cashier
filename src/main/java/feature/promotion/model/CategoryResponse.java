package feature.promotion.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryResponse {

     private int count;
     private CategoryResponseDetail[] data;

     @Setter
     @Getter
     public static class CategoryResponseDetail {
          private String catNameEn;
          private int id;
          private String catNameKh;
          private int movePosition;
          private int parentId;
     }
}
