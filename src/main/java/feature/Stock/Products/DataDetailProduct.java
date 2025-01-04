package feature.Stock.Products;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataDetailProduct {
    private Integer id;
    private BigDecimal cost;
    private BigDecimal price;
    private Integer qty_old;
    private String local_date;
}
