package com.project.appshippinglogistics.model;

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
@Table(name = "tbl_ship")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shi_id")
    private Integer id;
    @Column(name = "shi_fleet_number")
    private String fleetNumber;
    @Column(name = "shi_loading_capacity")
    private String loadingCapacity;
}
