package Model.Country;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountryModel {
    private String countryName;
    private String uuid;
    private Integer id;
    
    public CountryModel(){}
    
    public CountryModel(
            Integer id,
            String countryName,
            String uuid
    ){
        this.id = id;
        this.countryName = countryName;
        this.uuid = uuid;
    }
}
