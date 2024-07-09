package Model.Status;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ListStatusModel {
    private Integer count;
    private GetStatusModel[] data;
}
