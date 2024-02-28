package com.project.appshippinglogistics.service.implementation;

import com.project.appshippinglogistics.exception.BusinessException;
import com.project.appshippinglogistics.model.Ship;
import com.project.appshippinglogistics.repository.ShipRepository;
import com.project.appshippinglogistics.service.ShipService;
import com.project.appshippinglogistics.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    private ShipRepository shipRepository;

    @Autowired
    public ShipServiceImpl(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public List<Ship> getAll() {
        return shipRepository.findAll();
    }

    @Override
    public Ship getById(Integer id) {
        return shipRepository.findById(id).orElseThrow(() -> new BusinessException("401", HttpStatus.NOT_FOUND, Constants.MESSAGE_NOT_FOUND));
    }

    @Override
    public Ship save(Ship ship) {
        return shipRepository.save(ship);
    }
}
