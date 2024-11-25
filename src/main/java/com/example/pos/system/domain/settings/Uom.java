package com.example.pos.system.domain.settings;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="pos_uoms")
public class Uom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_en", nullable = false)
    private String uomNameEn;

    @Column(name = "name_kh")
    private String uomNameKh;

    @Column(name = "number_of_unit")
    private Integer numberOfUnit;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @CreationTimestamp
    @Column(name = "created_date")
    private Timestamp createdDate;
}
