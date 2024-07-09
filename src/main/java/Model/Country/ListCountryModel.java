package Model.Country;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ListCountryModel {
    private Integer count;
    private DataCountryModel[] data;
}
