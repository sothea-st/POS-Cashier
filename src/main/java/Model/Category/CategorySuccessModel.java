package Model.Category;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategorySuccessModel {
    private CategoryGetdataModel[] data;
    private Integer count;
}
