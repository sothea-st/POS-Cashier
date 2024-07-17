package Model.PurchaseOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ListPurchaseOrderModel {
    private Integer count;
    private DataPurchaseModel[] data;
}
