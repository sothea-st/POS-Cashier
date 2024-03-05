package com.example.pos.entity.people;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.ResponseEntity;

import com.example.pos.constant.JavaMessage;
import com.example.pos.constant.JavaValidation;

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
    private String cusName;
    private String contact;
    private String customerId;
    private String gender;
    private String nationality;

    @Column(name = "total_amount_earned")
    private BigDecimal totalAmountEarned;

    @Column(name = "point_earned")
    private int pointEarned = 0;

    @Column(name = "customer_type_id")
    private Integer customerTypeId;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

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
