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

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "pos_attribute")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "attr_name_en", nullable = false, length = 100)
    private String attrNameEn;

    @Column(name = "attr_name_kh", length = 100)
    private String attrNameKh;

    @Column(name = "status")
    private Boolean status = true;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
