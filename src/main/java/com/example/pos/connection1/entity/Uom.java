package com.example.pos.connection1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="pos_uoms")
public class Uom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name_en", nullable = false, length = 100)
    private String nameEn;

    @Column(name="name_kh", length = 100)
    private String nameKh;

    @Column(name="status")
    private Boolean status = true;

    @Column(name="is_deleted")
    private Boolean isDeleted = false;
}
