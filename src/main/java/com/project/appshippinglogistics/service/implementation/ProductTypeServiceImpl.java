package com.project.appshippinglogistics.service.implementation;

import com.project.appshippinglogistics.exception.BusinessException;
import com.project.appshippinglogistics.model.ProductType;
import com.project.appshippinglogistics.repository.ProductTypeRepository;
import com.project.appshippinglogistics.service.ProductTypeService;
import com.project.appshippinglogistics.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Override
    public ProductType getById(Integer idProductType) {
        return productTypeRepository.findById(idProductType).orElseThrow(() -> new BusinessException("401", HttpStatus.NOT_FOUND, Constants.MESSAGE_NOT_FOUND));
    }
}
