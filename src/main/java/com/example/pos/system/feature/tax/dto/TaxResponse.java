package com.example.pos.system.feature.tax.dto;
import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record TaxResponse(
    Integer id,
    String tax_name,
    BigDecimal rate_tax

) {

}
