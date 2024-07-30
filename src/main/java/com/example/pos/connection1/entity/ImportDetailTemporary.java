package com.example.pos.connection1.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "pos_import_detail_temporary")
public class ImportDetailTemporary {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     // @Column(name = "pro_id",length = 30)
     // private int productId;

     @ManyToOne
     private Import import1;

     @JoinColumn(name = "pro_id")
     @ManyToOne
     private Product product;

     @Column(name = "imp_id", length = 30)
     private int impId;

     @Column(name = "qty_new", length = 30)
     private int qtyNew = 0;

     @Column(name = "qty_old", length = 30)
     private Integer qtyOld = 0;

     @Column(name = "receive_qty", length = 30)
     private Integer receiveQty;

     @Column(name = "receive_msg")
     private String receiveMsg;

     @Column(name = "half_qty")
     private Integer halfQty;

     @Column(name = "cost", precision = 10, scale = 2)
     private BigDecimal cost;

     @Column(name = "amount", precision = 10, scale = 2)
     private BigDecimal amount;

     @Column(name = "expired_date", length = 30)
     private String expireDate;

     @Column(name = "create_by")
     private int createBy;

     @CreationTimestamp
     @Column(name = "create_date")
     private Date createDate;

     @Column(name = "status")
     private boolean status = true;

     @Column(name = "is_deleted")
     private boolean isDeleted = false;

}
