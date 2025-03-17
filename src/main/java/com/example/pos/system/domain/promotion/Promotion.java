package com.example.pos.system.domain.promotion;

import com.example.pos.system.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "pos_promotions")
@Builder
@AllArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "promotion",orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PromotionDetail> promotionDetails;

    @Column(name = "promotion_type",nullable = false)
    private String promotionType;

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date",nullable = false)
    private LocalDate endDate;

    @Column(name = "percentage",nullable = false)
    private Integer percentage;

    @Column(name = "total_price",nullable = false,precision = 10 , scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "after_discount",nullable = false,precision = 10 , scale = 2)
    private BigDecimal afterDiscount;

    @Column(name = "active")
    private Boolean active;


    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "status")
    private Boolean status;

}
