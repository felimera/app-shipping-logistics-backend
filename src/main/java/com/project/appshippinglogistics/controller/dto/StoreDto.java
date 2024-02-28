package com.project.appshippinglogistics.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDto {
    private Integer id;
    private Boolean availability;
    private Boolean national;
    private String address;
}
