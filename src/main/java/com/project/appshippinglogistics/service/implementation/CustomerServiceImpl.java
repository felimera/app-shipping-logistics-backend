package com.project.appshippinglogistics.service.implementation;

import com.project.appshippinglogistics.exception.BusinessException;
import com.project.appshippinglogistics.model.Customer;
import com.project.appshippinglogistics.model.User;
import com.project.appshippinglogistics.repository.CustomerRepository;
import com.project.appshippinglogistics.service.CustomerService;
import com.project.appshippinglogistics.service.UserService;
import com.project.appshippinglogistics.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private UserService userService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, UserService userService) {
        this.customerRepository = customerRepository;
        this.userService = userService;
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer postCustomer(Customer customer, Integer idUser) {
        if (this.isEmailExist(customer.getEmail())) {
            throw new BusinessException("300", HttpStatus.CONFLICT, "This email already exists.");
        }
        User user = userService.findById(idUser);
        customer.setUser(user);
        return customerRepository.save(customer);
    }

    private boolean isEmailExist(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }

    @Override
    public Customer getById(Integer id) {
        return customerRepository.findById(id).orElseThrow(() -> new BusinessException("201", HttpStatus.NOT_FOUND, Constants.MESSAGE_USER_NOT_FOUND));
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new BusinessException("201", HttpStatus.NOT_FOUND, Constants.MESSAGE_USER_NOT_FOUND));
    }
}
