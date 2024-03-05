package com.project.appshippinglogistics.service;

import com.project.appshippinglogistics.controller.dto.DeliveryDto;
import com.project.appshippinglogistics.controller.dto.search.DeliverySeek;
import com.project.appshippinglogistics.model.Delivery;

import java.util.List;

public interface DeliveryService {
    List<Delivery> getAll();

    Delivery getById(Integer id);

    Delivery save(Delivery delivery, DeliveryDto dto);

    List<Integer> getAllPrice();

    List<Integer> getAllAmount();

    List<Integer> getAllDiscount();

    List<Delivery> getDeliveryForMultiParameter(DeliverySeek seek);
}
