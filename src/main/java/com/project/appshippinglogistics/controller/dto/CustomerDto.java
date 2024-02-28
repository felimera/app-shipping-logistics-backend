package com.project.appshippinglogistics.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private Integer id;
    private String fullName;
    private String phone;
    private String email;
    private String gender;
    private Integer idUser;
}
