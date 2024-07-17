package Model.PurchaseOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DetailPurchaseModel {
    private Integer id;
    private Integer productId;
    private String barcode;
    private String proNameEn;
    private String proNameKh;
    private String division;
    private String department;
    private String category;
    private String subCategory;
    private Integer availableQty;
    private Integer orderQty;
    private Double cost;
    private Double totalCost;
    
    public DetailPurchaseModel(){};
    
    public DetailPurchaseModel(Integer id,
           Integer productId,
           String barcode,
           String proNameEn,
           String proNameKh,
           String division,
           String department,
           String category,
           String subCategory,
           Integer availableQty,
           Integer orderQty,
           Double cost,
           Double totalCost
    ){
           this.id = id;
           this.productId = productId;
           this.barcode = barcode;
           this.proNameEn = proNameEn;
           this.proNameKh = proNameKh;
           this.division = division;
           this.department = department;
           this.category = category;
           this.subCategory = subCategory;
           this.availableQty = availableQty;
           this.orderQty = orderQty;
           this.cost = cost;
           this.totalCost = totalCost;
           
           
    };
    
}
