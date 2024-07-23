package Model.PurchaseOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DataPurchaseModel {
     private Integer id;
     private String transactionNo;
     private String vendorName;
     private String referenceNo;
     private String transactionDate;
     private Integer totalQty;
     private String remark;
     private double totalCost;
}
