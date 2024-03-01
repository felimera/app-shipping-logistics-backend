package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.auth.SignUpDto;
import com.project.appshippinglogistics.controller.dto.exception.ExceptionResponseMessage;
import com.project.appshippinglogistics.exception.ResponseMessageException;
import com.project.appshippinglogistics.service.AuthService;
import com.project.appshippinglogistics.util.CadenaUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/signup")
@CrossOrigin(origins = "http://localhost:4200")
public class SignupController {

    AuthService authService;

    @Autowired
    public SignupController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Object> signUser(@Valid @RequestBody SignUpDto signUpDto, BindingResult bindingResult) {
        log.info("Creating SignUp : {}", signUpDto);
        if (bindingResult.hasErrors())
            throw new ResponseMessageException("401-01", "Error creating client.", CadenaUtil.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);

        boolean isUserCreated = authService.createUser(signUpDto);
        if (isUserCreated)
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ExceptionResponseMessage.builder().code(String.valueOf(HttpStatus.CREATED.value())).message("User created succesfully.").build());
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ExceptionResponseMessage.builder().code(String.valueOf(HttpStatus.BAD_REQUEST.value())).message("Failed created user.").build());
    }
}