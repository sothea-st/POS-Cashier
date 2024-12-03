package com.example.pos.system.domain.general;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "pos_device")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(nullable = false)
     private Integer id;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "count")
    private int count;

    @Column(name = "date")
    private String date;

}
