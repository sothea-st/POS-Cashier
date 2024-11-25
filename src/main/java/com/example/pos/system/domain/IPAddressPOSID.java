package com.example.pos.system.domain;

 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;


@Entity
@Table(name = "pos_id")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IPAddressPOSID {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @Column(name = "ip_address",length = 30)
     private String ipAddress;

     @Column(name = "device_name",length = 30)
     private String deviceName;

     @Column(name = "pos_id")
     private String posId;

     @Column(name = "user_id")
     private int userId;

}
