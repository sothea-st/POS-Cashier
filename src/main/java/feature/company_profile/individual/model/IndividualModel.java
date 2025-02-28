package feature.company_profile.individual.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IndividualModel {

     private int count;
     private List<IndividualDetail> data;

     @Setter
     @Getter
     public static class IndividualDetail {
          private Integer id;
          private String customerId;
          private String firstName;
          private String lastName;
          private String gender;
          private String nationality;
          private String phoneNumber;
          private String email;
          private String dob;
          private String profileImage;
          private String home;
          private String lat;
          private String lng;
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
