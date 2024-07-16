package Model.PurchaseOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DetailPurchaseModelThird {
    private String barcode;
    private String proNameEn;
    private String proNameKh;
    private String division;
    private String department;
    private String category;
    private String subCategory;
    private Integer subCategoryId;
    private Integer availableQty;
    private Integer orderQty;
    private double cost;
    private double totalCost;
}
