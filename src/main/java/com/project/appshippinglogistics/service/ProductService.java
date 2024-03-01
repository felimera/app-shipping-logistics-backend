package com.project.appshippinglogistics.service;

import com.project.appshippinglogistics.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product getById(Integer id);

    Product save(Product product,Integer idProductType);

    List<Product> getProductQuery(String value);
}
