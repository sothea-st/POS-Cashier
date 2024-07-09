package Model.Vendor;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DetailVendorModel {
    private Integer id;
    private String vendorName;
    private String address;
    private String contact;
    private String email;
    private String website;
    private String uuid;
    private String vdCode;
}
