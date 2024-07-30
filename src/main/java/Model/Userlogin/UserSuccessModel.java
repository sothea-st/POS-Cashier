package Model.Userlogin;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSuccessModel {
    private Integer count;
    private UserDataModel[] data;
}
