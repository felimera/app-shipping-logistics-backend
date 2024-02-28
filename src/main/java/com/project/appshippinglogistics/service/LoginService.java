package com.project.appshippinglogistics.service;

import com.project.appshippinglogistics.controller.dto.auth.LoginRequest;

public interface LoginService {
    String getTokenGenerator(LoginRequest loginRequest);

    String getGenerateTokenWithoutAuthorization(LoginRequest loginRequest);
}
