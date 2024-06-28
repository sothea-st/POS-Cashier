package Model.Userlogin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataModel {
    private String user_code;
    private Integer id;
    private Integer emp_id;
    private String full_name;
}
