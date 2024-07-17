package Model.PurchaseOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PurchaseModel {
    private Integer id;
    private String transactionNo;
    private String vendorName;
    private String referenceNo;
    private String transactionDate;
    private Integer totalQty;
    private double totalCost;
    
    public PurchaseModel(){};
    
    public PurchaseModel(Integer id,
           String transactionNo,
           String vendorName,
           String referenceNo,
           String transactionDate,
           Integer totalQty,
           double totalCost
    ){
        this.id = id;
        this.transactionNo = transactionNo;
        this.vendorName = vendorName;
        this.referenceNo = referenceNo;
        this.transactionDate = transactionDate;
        this.totalQty = totalQty;
        this.totalCost = totalCost;
        
    };
}
