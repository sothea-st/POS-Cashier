package Model.Category;

public class CategoryModel {

    public String getCatNameEn() {
        return catNameEn;
    }

    public void setCatNameEn(String catNameEn) {
        this.catNameEn = catNameEn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String catNameEn;
    private int id;
    
    public CategoryModel(int id,String catNameEn){
        this.id=id;
        this.catNameEn=catNameEn;
    }
}
