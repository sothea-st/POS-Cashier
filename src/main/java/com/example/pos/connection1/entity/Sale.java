package com.example.pos.connection1.entity;

import com.example.pos.connection1.constant.JavaMessage;
import com.example.pos.connection1.entity.payment.Payment;
import com.example.pos.connection1.entity.people.Customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pos_sale")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "pos_id")
    private String posId;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "sale_date")
    @NotNull(message = JavaMessage.required)
    @NotBlank(message = JavaMessage.required)
    private String saleDate;

    @Column(name = "discount" , precision = 10, scale = 2)
    @NotNull(message = JavaMessage.required)
    private BigDecimal discount ;

    @Column(name = "total" , precision = 10 , scale = 2)
    private BigDecimal total;



    @Column(name = "total_return" , precision = 10 , scale = 2)
    private BigDecimal totalReturn;

    @Column(name = "total_minus_total_return" , precision = 10 , scale = 2)
    private BigDecimal totalMinusTotalReturn;

    @Column(name = "active")
    private String active;


    @Column(name = "sub_total" , precision = 10 , scale = 2)
    private BigDecimal subTotal;

    @Column(name = "delivery_fee" , precision = 10 , scale = 2)
    private BigDecimal deliveryFee;

    @Column(name = "cus_id")
    private String cusId;

    @Column(name = "sale_is_return",length = 50)
    private String saleIsReturn;

    @Column(name = "discount_case",length = 50)
    private String discountCase;

    @OneToMany 
    private List<SaleDetail> dataSale;

    @OneToOne 
    @JoinColumn(name = "data_pay_id")
    private Payment dataPay;

 
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @Column(name = "create_by")
    private int createBy;

    @CreationTimestamp
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "status")
    private boolean status=true;

    @Column(name = "is_delete")
    private boolean isDelete=false;

    @Column(name = "date_local")
    private LocalDate dateLocal;

}
