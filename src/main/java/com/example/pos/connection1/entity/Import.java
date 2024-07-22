package com.example.pos.connection1.entity;

import com.example.pos.connection1.constant.JavaMessage;
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
import java.util.*;

@Entity
@Table(name = "pos_import")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Import {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "imp_no",length = 50)
    private String impNo;

    @JoinColumn(name = "emp_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "vendor_id")
    @ManyToOne
    private Vendor vendor;

    @Column(name = "transaction_date")
    private String transactionDate;

    @Column(name = "reference_no")
    private String referenceNo;

    @Column(name = "imp_date",length = 50)
    @NotNull(message = JavaMessage.required)
    @NotBlank(message = JavaMessage.required)
    private String impDate;

    @Column(name = "discount",length = 20, precision = 10, scale = 2)
    private BigDecimal discount;

    @Column(name = "total",precision = 10,scale = 2)
    @NotNull(message = JavaMessage.required)
    private BigDecimal total;

    @Column(name = "total_qty")
    private Integer totalQty;

    @OneToMany(mappedBy = "imps")
    private List<ImportDetail> details;


    @OneToMany(mappedBy = "import1", fetch = FetchType.EAGER)
    private List<ImportDetailTemporary> importDetailTemporaries;
  
    @Column(name = "remark")
    private String remark;

    @Column(name = "create_by")
    private int createBy;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createDate;

    @Column(name = "status")
    private boolean status=true;

    @Column(name = "is_deleted")
    private boolean isDeleted=false;

    @Column(name = "date_local")
    private LocalDate dateLocal;

}
