package feature.company_profile.business.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessModel {

     private int count;
     private List<BusinessModel> data;

     @Setter
     @Getter
     public static class BusinessModelDetail {

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
     }

}
