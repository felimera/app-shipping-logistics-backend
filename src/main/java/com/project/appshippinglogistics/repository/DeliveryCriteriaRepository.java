package com.project.appshippinglogistics.repository;

import com.project.appshippinglogistics.controller.dto.search.DeliverySeek;
import com.project.appshippinglogistics.model.Delivery;

import java.util.List;

public interface DeliveryCriteriaRepository {
    List<Delivery> getDeliveryForMultiParameter(DeliverySeek seek);
}
