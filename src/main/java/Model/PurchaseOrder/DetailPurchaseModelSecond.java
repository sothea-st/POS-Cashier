package Model.PurchaseOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DetailPurchaseModelSecond {
    private Integer transactionNo;
    private String purchaseOrderNo;
    private String transactionDate;
    private Integer vendorId;
    private String vendorName;
    private Integer totalQty;
    private double totalCost;
    private String referenceNo;
    private String orderDate;
    private DetailPurchaseModelThird[] details;
}
