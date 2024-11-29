package Model.Reason;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReasonModel {

     private ReasonDetail data;
     private String msg;

     @Setter
     @Getter
     public static class ReasonDetail {

          private int id;
          private String reason;
          private String code;
          private int createBy;
          private String createDate;
          private boolean status;
          private boolean deleted;
     }
}
