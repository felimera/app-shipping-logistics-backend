package com.project.appshippinglogistics.service;

import com.project.appshippinglogistics.model.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAll();

    Vehicle getById(Integer id);

    Vehicle save(Vehicle vehicle);

    List<Vehicle> getStoreQuery(String value);
}
