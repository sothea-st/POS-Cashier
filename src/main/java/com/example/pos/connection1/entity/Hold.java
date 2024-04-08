package com.example.pos.connection1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Entity
@Table(name = "pos_hold")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "note", length = 50)
    private String note;

    @Column(name = "qty_hold", length = 50)
    private int qtyHole;
    
    @OneToMany
    private List<HoldData> listHoldDetail;

    @Column(name = "reason_id", length = 50)
    private Integer reasonId;

    @Column(name = "create_by")
    private int createBy;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createDate;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

}
