package Model.Vendor;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class VendorModel {
    private Integer id;
    private String vendorName;
    private String address;
    private String contact;
    private String email;
    private String website;
    private String uuid;
    private String vdCode;
    
    public VendorModel(){}
    
    public VendorModel(Integer id,
            String vendorName,
            String address,
            String contact,
            String email,
            String website,
            String uuid,
            String vdCode
    ){
        this.id = id;
        this.vendorName = vendorName;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.website = website;
        this.uuid = uuid;
        this.vdCode = vdCode;
    }
}
