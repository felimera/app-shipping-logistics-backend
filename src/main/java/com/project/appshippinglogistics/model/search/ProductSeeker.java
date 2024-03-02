package com.project.appshippinglogistics.model.search;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductSeeker {
    private String name;
    private Integer price;
    private Integer startAmount;
    private Integer finalAmount;
    private LocalDate startDate;
    private LocalDate finalDate;
    private Integer idProductType;
}
