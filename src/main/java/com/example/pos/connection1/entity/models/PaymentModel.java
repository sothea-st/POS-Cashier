package com.example.pos.connection1.entity.models;
import java.math.*;
public interface PaymentModel {

     BigDecimal getReceive_usd();
     BigDecimal getReceive_khr();
     BigDecimal getChange_khr();
     BigDecimal getChange_usd();
     BigDecimal getTotal();

}
