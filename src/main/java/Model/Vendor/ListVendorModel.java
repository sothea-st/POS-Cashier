package Model.Vendor;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter

public class ListVendorModel {
    private Integer count;
    private DataVendorModel[] content;
}
