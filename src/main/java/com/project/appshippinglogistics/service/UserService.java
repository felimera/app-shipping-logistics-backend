package com.project.appshippinglogistics.service;

import com.project.appshippinglogistics.model.User;

public interface UserService {
    User findById(Integer idUser);

    boolean isExistUser(String email);
}
