package com.project.appshippinglogistics.service;

import com.project.appshippinglogistics.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<Customer> getAll();

    Customer getById(Integer id);

    Customer postCustomer(Customer customer, Integer idUser);
}
