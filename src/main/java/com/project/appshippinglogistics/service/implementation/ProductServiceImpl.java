package com.project.appshippinglogistics.service.implementation;

import com.project.appshippinglogistics.exception.BusinessException;
import com.project.appshippinglogistics.model.Product;
import com.project.appshippinglogistics.model.ProductType;
import com.project.appshippinglogistics.repository.ProductRepository;
import com.project.appshippinglogistics.service.ProductService;
import com.project.appshippinglogistics.service.ProductTypeService;
import com.project.appshippinglogistics.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductTypeService productTypeService;

    public ProductServiceImpl(ProductRepository productRepository, ProductTypeService productTypeService) {
        this.productRepository = productRepository;
        this.productTypeService = productTypeService;
    }

    @Autowired


    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new BusinessException("401", HttpStatus.NOT_FOUND, Constants.MESSAGE_NOT_FOUND));
    }

    @Override
    public Product save(Product product, Integer idProductType) {
        ProductType productType =productTypeService.getById(idProductType);
        product.setProductType(productType);
        return productRepository.save(product);
    }
}
