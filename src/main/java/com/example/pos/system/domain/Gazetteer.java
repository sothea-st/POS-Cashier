package com.example.pos.system.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "ma_gazetteers")
public class Gazetteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name_latin")
    private String nameLatin;

    @Column(name = "reference")
    private String reference;

    @Column(name = "official_note")
    private String officialNote;

    @Column(name = "note")
    private String note;

    @Column(name = "type")
    private String type;

    @Column(name = "latlg")
    private String latLg;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "name_kh")
    private String nameKh;

    @Column(name = "create_by")
    private Integer createBy;

    @CreationTimestamp
    @Column(name = "create_date")
    private Timestamp createData;

}
