package com.project.appshippinglogistics.model;


import com.project.appshippinglogistics.model.complement.Gender;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cus_id")
    private Integer id;
    @Column(name = "cus_full_name")
    private String fullName;
    @Column(name = "cus_phone")
    @Nullable
    private String phone;
    @Column(name = "cus_email")
    private String email;
    @Column(name = "cus_gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cus_user_id")
    private User user;
}
