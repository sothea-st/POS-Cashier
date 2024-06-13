package Model.Staff;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StaffDataSuccessModel {
    private String msg;
    private StaffGetDataModel[] data;
}
