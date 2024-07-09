package Model.Status;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
 
public class StatusModel {
    private String statusName;
    private Integer id;
    
    public StatusModel(){}
    
    public StatusModel(
            Integer id,
            String statusName
    ){
        this.id = id;
        this.statusName = statusName;
    }
}
