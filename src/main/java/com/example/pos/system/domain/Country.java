package com.example.pos.system.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "pos_countries")
@Builder
@AllArgsConstructor
public class Country {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     @Column(name = "uuid",length = 100)
     private String uuid;

     @Column(name = "country_name",length = 100)
     private String countryName;

     @Column(name = "create_by",length = 20)
     private int createBy;

     @CreationTimestamp
     @Column(name = "create_date")
     private Date createDate;

     @Column(name = "status")
     private boolean status=true;
     
     @Column(name = "is_deleted")
     private boolean isDeleted=false;

}
