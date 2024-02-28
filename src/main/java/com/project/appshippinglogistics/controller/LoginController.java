package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.auth.LoginRequest;
import com.project.appshippinglogistics.controller.dto.auth.LoginResponse;
import com.project.appshippinglogistics.service.LoginService;
import com.project.appshippinglogistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login")
public class LoginController {
    private UserService userService;

    private LoginService loginService;

    @Autowired
    public LoginController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        userService.isExistUser(loginRequest.getEmail());

        String jwt = loginService.getTokenGenerator(loginRequest);
        // Aditional logic can here be added here if needed.
        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}
