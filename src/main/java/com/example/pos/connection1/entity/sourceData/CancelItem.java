package com.example.pos.connection1.entity.sourceData;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@Entity
@Table(name = "pos_cancel_item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CancelItem {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @Column(name = "reason_id")
     private int reasonId;

     @OneToMany(cascade = CascadeType.ALL)
     private List<CancelItemDetail> listCancelDetail;

     @Column(name = "code")
     private String code;
     @Column(name = "cancel_time")
     private String cancelTime;
     @Column(name = "cancel_date")
     private String cancelDate;

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
