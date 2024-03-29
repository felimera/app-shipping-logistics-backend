package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.ProductTypeDto;
import com.project.appshippinglogistics.mapper.ProductTypeMapper;
import com.project.appshippinglogistics.model.ProductType;
import com.project.appshippinglogistics.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/producttype")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping(path = "/query")
    public ResponseEntity<List<ProductTypeDto>> getProductTypeQuery(@RequestParam(name = "value") String value) {
        List<ProductType> productTypeList = productTypeService.getProductTypeQuery(value);
        return ResponseEntity.ok(productTypeList.stream().map(ProductTypeMapper.INSTANCE::toDto).toList());
    }
}
