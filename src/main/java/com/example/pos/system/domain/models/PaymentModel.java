package com.example.pos.system.domain.models;
import java.math.*;
public interface PaymentModel {

     BigDecimal getReceive_usd();
     BigDecimal getReceive_khr();
     BigDecimal getChange_khr();
     BigDecimal getChange_usd();
     BigDecimal getTotal();

}
