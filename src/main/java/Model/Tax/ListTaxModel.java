package Model.Tax;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ListTaxModel {
    private Integer count;
    private DataTaxModel[] data;
}
