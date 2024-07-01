package com.example.pos.connection1.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "pos_vendors")
public class Vendor {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @Column(name = "vendor_name",nullable = false)
     private String vendorName;

     @Column(name = "uuid",nullable = false,unique = true)
     private String uuid;

     @Column(name = "address",nullable = false)
     private String address;

     @Column(name = "contact",nullable = false,length = 12 , unique = true)
     private String contact;

     @Column(name = "email",length = 50 , unique = true)
     private String email;

     @Column(name = "website",length = 255)
     private String website;

     @Column(name = "vendor_code",length = 20,nullable = false)
     private String vendorCode;

     @Column(name = "create_by",length = 20)
     private int createBy;

     @CreationTimestamp
     @Column(name = "create_date")
     private Date createDate;

     @Column(name = "status")
     private boolean status=true;

     @Column(name = "is_deleted")
     private boolean isDeleted=false;

     @ManyToMany
     @JoinTable(
          name = "pos_product_vendor_detail",
          joinColumns = @JoinColumn(name ="vendor_id"),
          inverseJoinColumns = @JoinColumn(name ="product_id")
     )
     private List<Product> products;
}
