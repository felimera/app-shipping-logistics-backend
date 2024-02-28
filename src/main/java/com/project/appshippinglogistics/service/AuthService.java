package com.project.appshippinglogistics.service;

import com.project.appshippinglogistics.controller.dto.SignUpDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    boolean createUser(SignUpDto signUpDto);
}
