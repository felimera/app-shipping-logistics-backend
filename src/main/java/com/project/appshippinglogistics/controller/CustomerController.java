package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.CustomerDto;
import com.project.appshippinglogistics.mapper.CustomerMapper;
import com.project.appshippinglogistics.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "query")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok(CustomerMapper.INSTANCE.toDto(customerService.getCustomerByEmail(email)));
    }
}
