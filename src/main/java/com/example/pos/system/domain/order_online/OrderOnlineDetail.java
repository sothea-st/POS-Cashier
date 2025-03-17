package com.example.pos.system.domain.order_online;

import com.example.pos.system.domain.settings.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "pos_order_online_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOnlineDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @Column(name = "qty_sale",nullable = false)
    private Integer qtySale;

    @Column(name = "discount_price",scale = 2, precision = 10)
    private BigDecimal discountPrice;

    @ManyToOne
    private OrderOnline orderOnline;

}
