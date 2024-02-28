package com.project.appshippinglogistics.service.implementation;

import com.project.appshippinglogistics.controller.dto.SignUpDto;
import com.project.appshippinglogistics.exception.BusinessException;
import com.project.appshippinglogistics.mapper.CustomerMapper;
import com.project.appshippinglogistics.mapper.UserMapper;
import com.project.appshippinglogistics.model.Customer;
import com.project.appshippinglogistics.model.User;
import com.project.appshippinglogistics.repository.UserRepository;
import com.project.appshippinglogistics.service.AuthService;
import com.project.appshippinglogistics.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    CustomerService customerService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CustomerService customerService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerService = customerService;
    }

    @Override
    @Transactional
    public boolean createUser(SignUpDto signUpDto) {

        if (this.isEmailExist(signUpDto.getEmail())) {
            throw new BusinessException("300", HttpStatus.CONFLICT, "This email already exists.");
        }

        String hashPassword = passwordEncoder.encode(signUpDto.getPassword());
        User user = UserMapper.INSTANCE.toSignUp(signUpDto);
        user.setPassword(hashPassword);

        User entity = userRepository.save(user);

        Customer customer = CustomerMapper.INSTANCE.toSignUpDto(signUpDto);
        customerService.postCustomer(customer, entity.getId());

        return true;
    }

    private boolean isEmailExist(String email) {
        return userRepository.findOneByEmail(email).isPresent();
    }
}
