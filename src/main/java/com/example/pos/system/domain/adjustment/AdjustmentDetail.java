package com.example.pos.system.domain.adjustment;

import com.example.pos.system.domain.settings.Category;
import com.example.pos.system.domain.settings.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "pos_adjustment_details")
@Builder
@AllArgsConstructor
public class AdjustmentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "adjustment_id")
    private Adjustment adjustment;


    @Column(name = "qty",nullable = false)
    private Long qty;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private Category subCategory;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Category department;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Category division;

}
