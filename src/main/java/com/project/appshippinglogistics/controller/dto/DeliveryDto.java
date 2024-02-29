package com.project.appshippinglogistics.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DeliveryDto {
    private Integer id;
    private String guideNumber;
    private Integer price;
    private Integer amount;
    private Double discount;
    private LocalDate deadline;

    private Integer idCustomer;
    private Integer idProduct;
    private Integer idStore;
    private Integer idVehicle;
    private Integer idPort;
    private Integer idShip;
}
