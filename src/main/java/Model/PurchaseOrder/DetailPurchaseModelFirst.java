package Model.PurchaseOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DetailPurchaseModelFirst {
    private String status;
    private String msg;
    private DetailPurchaseModelSecond data;
}
