package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.ProductTypeDto;
import com.project.appshippinglogistics.mapper.ProductTypeMapper;
import com.project.appshippinglogistics.model.ProductType;
import com.project.appshippinglogistics.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/producttype")
public class ProductTypeController {

    private ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping
    public ResponseEntity<List<ProductTypeDto>> getAll() {
        List<ProductType> productTypeList = productTypeService.getAll();
        return ResponseEntity.ok(productTypeList.stream().map(ProductTypeMapper.INSTANCE::toDto).toList());
    }
}
