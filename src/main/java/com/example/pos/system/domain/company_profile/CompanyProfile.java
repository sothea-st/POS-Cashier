package com.example.pos.system.domain.company_profile;

import com.example.pos.system.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pos_company_profiles")
public class CompanyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name",length = 50)
    private String firstName;

    @Column(name = "last_name",length = 50)
    private String lastName;

    @Column(name = "gender",length = 20)
    private String gender;

    @Column(name = "nationality",length = 50)
    private String nationality;

    @Column(name = "phone_name",length = 12,nullable = false)
    private String phoneNumber;

    @Column(name = "email",length = 50,nullable = false)
    private String email;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "home")
    private String home;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    @Column(name = "street")
    private String street;

    @Column(name = "province")
    private String province;

    @Column(name = "district")
    private String district;

    @Column(name = "commune")
    private String commune;

    @Column(name = "village")
    private String village;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "vat_number")
    private String vatNumber;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @CreationTimestamp
    @Column(name = "created_date")
    private Timestamp createdDate;

}
