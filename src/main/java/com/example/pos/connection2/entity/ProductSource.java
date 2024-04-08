package com.example.pos.connection2.entity;

 
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Min;
import java.util.Date;

import com.example.pos.connection1.constant.JavaMessage;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@Entity
@Table(name = "product_by_categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSource {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category_id")
    private int categoryId;
   
    @Column(name = "name")
    private String name;

    @Column(name = "name_kh")
    private String nameKh;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name="cost")
    private BigDecimal cost;

     @Column(name = "status")
     private String status;

     @Column(name = "barcode")
     private String barcode;

     @Column(name = "discount")
     private BigDecimal discount;

     @Column(name = "brand")
     private String brand;
 
}
