package Model.Staff;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StaffDataSuccessModel {
    private Integer count;
    private StaffGetDataModel[] data;
}
