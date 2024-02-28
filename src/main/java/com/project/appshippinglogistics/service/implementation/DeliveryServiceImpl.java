package com.project.appshippinglogistics.service.implementation;

import com.project.appshippinglogistics.controller.dto.DeliveryDto;
import com.project.appshippinglogistics.exception.BusinessException;
import com.project.appshippinglogistics.model.Delivery;
import com.project.appshippinglogistics.repository.*;
import com.project.appshippinglogistics.service.CustomerService;
import com.project.appshippinglogistics.service.DeliveryService;
import com.project.appshippinglogistics.service.ProductService;
import com.project.appshippinglogistics.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private DeliveryRepository deliveryRepository;
    private CustomerService customerService;
    private ProductService productService;
    private StoreRepository storeRepository;
    private VehicleRepository vehicleRepository;
    private PortRepository portRepository;
    private ShipRepository shipRepository;

    @Autowired
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, CustomerService customerService, ProductService productService, StoreRepository storeRepository, VehicleRepository vehicleRepository, PortRepository portRepository, ShipRepository shipRepository) {
        this.deliveryRepository = deliveryRepository;
        this.customerService = customerService;
        this.productService = productService;
        this.storeRepository = storeRepository;
        this.vehicleRepository = vehicleRepository;
        this.portRepository = portRepository;
        this.shipRepository = shipRepository;
    }

    @Override
    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery getById(Integer id) {
        return deliveryRepository.findById(id).orElseThrow(() -> new BusinessException("401", HttpStatus.NOT_FOUND, Constants.MESSAGE_NOT_FOUND));
    }

    @Override
    public Delivery save(Delivery delivery, DeliveryDto dto) {
        delivery.setCustomer(customerService.getById(dto.getIdCustomer()));
        delivery.setProduct(productService.getById(dto.getIdProduct()));

        if (Objects.nonNull(dto.getIdStore()))
            storeRepository.findById(dto.getIdStore()).ifPresent(delivery::setStore);
        if (Objects.nonNull(dto.getIdVehicle()))
            vehicleRepository.findById(dto.getIdVehicle()).ifPresent(delivery::setVehicle);
        if (Objects.nonNull(dto.getIdPort()))
            portRepository.findById(dto.getIdPort()).ifPresent(delivery::setPort);
        if (Objects.nonNull(dto.getIdShip()))
            shipRepository.findById(dto.getIdShip()).ifPresent(delivery::setShip);

        return deliveryRepository.save(delivery);
    }

}
