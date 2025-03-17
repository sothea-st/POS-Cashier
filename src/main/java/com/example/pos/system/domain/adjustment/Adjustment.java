package com.example.pos.system.domain.adjustment;

import com.example.pos.system.domain.User;
import com.example.pos.system.domain.sourceData.Reason;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "pos_adjustments")
@Builder
@AllArgsConstructor
public class Adjustment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reason_id")
    private Reason reason;

    @Column(name = "reference",nullable = false)
    private String reference;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "transaction",nullable = false)
    private String transaction;

    @Column(name = "total_qty",nullable = false)
    private Long totalQty;

    @Column(nullable = false,name = "total_cost",precision = 10, scale = 2)
    private BigDecimal totalCost;

    @OneToMany(mappedBy = "adjustment",orphanRemoval = true,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AdjustmentDetail> adjustmentDetails;

    @Column(name = "status")
    private String status;

    @Column(name = "pos_date")
    private LocalDate postDate;

    @ManyToOne
    @JoinColumn(name = "approve_by")
    private User approveBy;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
