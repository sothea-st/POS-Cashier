package com.example.pos.projections.CustomerPointProjection;

import java.math.BigDecimal;

public interface CustomerPointProjection {
     int getpoint_earned();
     String getCustomer_id();
     String getContact();
     BigDecimal gettotal_amount_earned();
}
