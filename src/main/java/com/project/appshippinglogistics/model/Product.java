package com.project.appshippinglogistics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Integer id;
    @Column(name = "pro_name")
    private String name;
    @Column(name = "pro_price")
    private Integer price;
    @Column(name = "pro_amount")
    private Integer amount;
    @Column(name = "pro_registration_date")
    private LocalDate registrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_pt_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ProductType productType;
}
