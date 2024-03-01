package com.project.appshippinglogistics.model;

import com.project.appshippinglogistics.util.config.BooleanConverter;
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
@Table(name = "tbl_store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sto_id")
    private Integer id;
    @Column(name = "sto_availability")
    @Convert(converter = BooleanConverter.class)
    private Boolean availability;
    @Column(name = "sto_national")
    @Convert(converter = BooleanConverter.class)
    private Boolean national;
    @Column(name = "sto_address")
    private String address;
}
