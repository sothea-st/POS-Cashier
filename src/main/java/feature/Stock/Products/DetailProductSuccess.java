package feature.Stock.Products;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DetailProductSuccess {
   private long status;
   private String msg;
   private DataDetailProduct[] data;
}
