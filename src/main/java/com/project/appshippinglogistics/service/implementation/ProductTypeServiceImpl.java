package com.project.appshippinglogistics.service.implementation;

import com.project.appshippinglogistics.model.ProductType;
import com.project.appshippinglogistics.repository.ProductTypeRepository;
import com.project.appshippinglogistics.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    private ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public List<ProductType> getAll() {
        return productTypeRepository.findAll();
    }
}
