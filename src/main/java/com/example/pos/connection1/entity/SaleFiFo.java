package com.example.pos.connection1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pos_sale_fifo")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class SaleFiFo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Product product;

    @ManyToOne
    @JoinColumn(name = "imp_id")
    private Import anImport;

    @Column(name = "sale_qty")
    private Integer saleQty;

    @Column(name = "payment_no")
    private String paymentNo;

    @Column(name = "local_date")
    private LocalDate localDate;

    @Column(name = "return_qty")
    private Integer returnQty;

}
