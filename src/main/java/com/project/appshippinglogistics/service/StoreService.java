package com.project.appshippinglogistics.service;

import com.project.appshippinglogistics.model.Store;

import java.util.List;

public interface StoreService {
    List<Store> getAll();

    Store getById(Integer id);

    Store save(Store store);
}
