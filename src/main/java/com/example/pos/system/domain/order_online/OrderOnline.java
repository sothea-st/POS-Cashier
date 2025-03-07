package com.example.pos.system.domain.order_online;

import com.example.pos.system.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pos_order_onlines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOnline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "create_by")
    private User createBy;

    @CreationTimestamp
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "is_deleted")
    private Boolean isDeleted ;

    @Column(name = "order_date",nullable = false)
    private LocalDate orderDate;

    @Column(name = "order_number",nullable = false,unique = true)
    private String orderNumber;

    @Column(name = "order_status",nullable = false)
    private String orderStatus;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "phone_number",length = 12)
    private String phoneNumber;

    @Column(name = "delivery_information")
    private String deliveryInformation;

    @Column(name = "total_amount",nullable = false,scale = 2,precision = 10)
    private BigDecimal totalAmount;

    @Column(name = "payment_method",nullable = false)
    private String paymentMethod;

    @Column(name = "payment_status",nullable = false)
    private String paymentStatus;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "customer_note")
    private String customerNote;

    @Column(name = "sub_total",nullable = false,scale = 2,precision = 10)
    private BigDecimal subTotal;

    @Column(name = "discount",scale = 2,precision = 10)
    private BigDecimal discount;

    @Column(name = "delivery_fee",scale = 2,precision = 10)
    private BigDecimal deliveryFee;

    @Column(name = "grand_total",scale = 2,precision = 10,nullable = false)
    private BigDecimal grandTotal;

    @OneToMany(mappedBy = "orderOnline",orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderOnlineDetail> orderOnlineDetails;

    @Column(name = "accept")
    private String accept;

}
