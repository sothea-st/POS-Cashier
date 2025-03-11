package feature.report.report_vendor.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import pagination.PaginationData;

@Setter
@Getter
public class ReportVendorResponse implements PaginationData{

     private int count;
     private List<ReportVendorResponseDetail> data;

     @Setter
     @Getter
     public static class ReportVendorResponseDetail {

          private int id;
          private String vendorName;
          private String address;
          private String contact;
          private String email;
          private String website;
          private String uuid;
          private String vdCode;
     }

}
