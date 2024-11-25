package com.example.pos.system.layer.projections.CustomerPointProjection;

import java.math.BigDecimal;

public interface CustomerPointProjection {
     Integer getpoint_earned();
     String getCustomer_id();
     String getContact();
     BigDecimal gettotal_amount_earned();
}
