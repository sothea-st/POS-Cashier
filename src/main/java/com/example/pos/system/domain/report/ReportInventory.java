package com.example.pos.system.domain.report;

import com.example.pos.system.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "pos_report_inventories")
public class ReportInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "beginning_qty")
    private Integer beginningQty;

    @Column(name = "stock_in_qty")
    private Integer stockInQty;

    @Column(name = "available_qty")
    private Integer availableQty;

    @Column(name = "stock_out_qty")
    private Integer stockOutQty;

    @Column(name = "return_in_qty")
    private Integer returnInQty;

    @Column(name = "return_out_qty")
    private Integer returnOutQty;

    @Column(name = "ending_qty")
    private Integer endingQty;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createDate;

}
