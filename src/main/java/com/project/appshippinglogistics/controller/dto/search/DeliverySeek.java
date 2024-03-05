package com.project.appshippinglogistics.controller.dto.search;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class DeliverySeek {
    private  String guideNumber;
    private  Integer price;
    private  Integer amount;
    private  Integer discount;
    private LocalDate initialDeadline;
    private LocalDate finalDeadline;
    private Integer idCustomer;
    private Integer idProduct;
    private Integer idStore;
    private Integer idVehicle;
    private Integer idPort;
    private Integer idShip;
}
