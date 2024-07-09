package Model.Tax;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaxModel {
    private Integer id;
    private String taxName;
    private BigDecimal rateTax;
    
    public TaxModel(){}
    
    public TaxModel(
            Integer id,
            String tax_name,
            BigDecimal rate_tax
    ){
        this.id = id;
        this.taxName = tax_name;
        this.rateTax = rate_tax;
        
    }
    
}
