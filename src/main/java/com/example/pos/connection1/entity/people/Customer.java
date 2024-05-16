package com.example.pos.connection1.entity.people;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.pos.connection1.constant.JavaMessage;
import com.example.pos.connection1.constant.JavaValidation;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.ResponseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pos_customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = JavaMessage.required)
    @NotNull(message = JavaMessage.required)
    @Column(name = "cus_name")
    private String cusName;
    @Column(name = "contact")
    private String contact;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "gender")
    private String gender;
    @Column(name = "nationality")
    private String nationality;


    @Column(name = "coupon")
    private String coupon;

    @Column(name = "total_amount_earned")
    private BigDecimal totalAmountEarned;

    @Column(name = "point_earned")
    private Double pointEarned;

    @Column(name = "customer_type_id")
    private Integer customerTypeId;

    // @Column(name = "source_id")
    // private Integer sourceId;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Column(name = "email")
    private String email;

    @CreationTimestamp
    @Column(updatable = false, name = "create_date")
    private Date createDate;

    @Column(name = "create_by")
    private int createBy;

    public ResponseEntity<?> checkPhone(String c) {
        Map<String, Object> err = new HashMap<>();
        String keyContact = "contact";
        String contact = JavaValidation.checkPhone(c);
        if (!contact.isEmpty()) {
            err.put("msg", contact);
            err.put("status", 500);
            return ResponseEntity.ok().body(err);
        }
        return null;
    }

}
