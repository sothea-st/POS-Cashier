package Model.Tax;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetailTaxModel {
    private Integer id;
    private String tax_name;
    private BigDecimal rate_tax;
}
