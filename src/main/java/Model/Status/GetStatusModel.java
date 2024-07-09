package Model.Status;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class GetStatusModel {
    private Integer id;
    private String statusName;
    private Boolean status;
    private Boolean isDeleted;
}
