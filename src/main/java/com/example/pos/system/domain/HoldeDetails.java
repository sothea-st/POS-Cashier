package com.example.pos.system.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.*;
@Entity
@Table(name = "pos_hold_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoldeDetails {
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

     @Column(name = "status")
     private boolean status = true;
 
     @Column(name = "is_deleted")
     private boolean isDeleted = false;

}
