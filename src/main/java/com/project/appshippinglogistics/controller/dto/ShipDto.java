package com.project.appshippinglogistics.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipDto {
    private Integer id;
    private String fleetNumber;
    private String loadingCapacity;
}
