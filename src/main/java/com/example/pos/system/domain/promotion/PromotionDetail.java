package com.example.pos.system.domain.promotion;

import com.example.pos.system.domain.settings.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "pos_promotion_details")
@Builder
@AllArgsConstructor
public class PromotionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "promotion_id",nullable = false)
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @Column(name = "percentage_detail",nullable = false)
    private Integer percentageDetail;

    @Column(name = "after_discount",precision = 10 , scale = 2,nullable = false)
    private BigDecimal afterDiscount;

}
