package feature.company_profile.business.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import pagination.PaginationData;

@Setter
@Getter
public class BusinessModel implements PaginationData {

     private int count;
     private List<BusinessModelDetail> data;
 
     @Setter
     @Getter
     public static class BusinessModelDetail {
          private Integer id;
          private String customerName;
          private String companyName;
          private String phoneNumber;
          private String email;
          private String vatNumber;
          private String home;
          private String street;
          private String province;
          private String district;
          private String commune;
          private String village;
          private String fullAddressKh;
          private String fullAddressEn;
          private String createdDate;
     }

}
