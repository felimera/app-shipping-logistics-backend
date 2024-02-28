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
@Table(name = "tbl_port")
public class Port {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "por_id")
    private Integer id;
    @Column(name = "por_availability")
    @Convert(converter = BooleanConverter.class)
    private Boolean availability;
    @Column(name = "por_national")
    @Convert(converter = BooleanConverter.class)
    private Boolean national;
    @Column(name = "por_location")
    private String location;
}
