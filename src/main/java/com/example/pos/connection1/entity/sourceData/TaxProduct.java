package com.example.pos.connection1.entity.sourceData;
import java.math.BigDecimal;
import java.util.Date;

import com.example.pos.connection1.constant.JavaMessage;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_product_tax")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaxProduct {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
 
     @Column(name = "tax_name")
     @NotBlank(message = JavaMessage.required)
     @NotNull(message = JavaMessage.required)
     private String taxName;

     @Column(name = "rate_tax",precision = 2)
     private BigDecimal rateTax;

     @Column(name = "status")
     private boolean status = true;
 
     @Column(name = "is_deleted")
     private boolean isDeleted = false;
 
     @CreationTimestamp
     @Column(updatable = false, name = "create_date")
     private Date createDate;
 
     @Column(name = "create_by")
     private int createBy;


}
