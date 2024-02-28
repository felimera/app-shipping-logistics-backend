package com.project.appshippinglogistics.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String gender;
}
