package com.project.appshippinglogistics.service;

import com.project.appshippinglogistics.model.Ship;

import java.util.List;

public interface ShipService {
    List<Ship> getAll();

    Ship getById(Integer id);

    Ship save(Ship ship);
}
