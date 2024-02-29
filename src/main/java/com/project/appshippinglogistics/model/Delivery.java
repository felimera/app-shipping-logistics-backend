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
@Table(name = "tbl_delivery_service")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ds_id")
    private Integer id;
    @Column(name = "ds_guide_number")
    private String guideNumber;
    @Column(name = "ds_price")
    private Integer price;
    @Column(name = "ds_amount")
    private Integer amount;
    @Column(name = "ds_discount")
    private Double discount;
    @Column(name = "ds_deadline")
    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ds_cus_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ds_pro_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ds_sto_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Store store;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ds_veh_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Vehicle vehicle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ds_por_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Port port;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ds_shi_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Ship ship;
}
