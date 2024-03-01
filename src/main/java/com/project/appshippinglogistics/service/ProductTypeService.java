package com.project.appshippinglogistics.service;

import com.project.appshippinglogistics.model.ProductType;

import java.util.List;

public interface ProductTypeService {
    List<ProductType> getAll();

    ProductType getById(Integer idProductType);
}
