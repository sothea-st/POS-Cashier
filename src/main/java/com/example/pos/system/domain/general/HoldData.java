package com.example.pos.system.domain.general;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoldData {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @Column(name = "pro_id", length = 50)
     private int proId;

     @Column(name = "hold_id", length = 50)
     private int holdId;

     @Column(name = "discount", scale =2,precision = 10)
     private BigDecimal discount;

     @Column(name = "discount_type")
     private String discountType;

     @Column(name = "qty_hold", length = 50)
     private int qtyHold;
}
