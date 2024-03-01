package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.ProductDto;
import com.project.appshippinglogistics.exception.ResponseMessageException;
import com.project.appshippinglogistics.mapper.ProductMapper;
import com.project.appshippinglogistics.model.Product;
import com.project.appshippinglogistics.service.ProductService;
import com.project.appshippinglogistics.util.CadenaUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        List<Product> productList = productService.getAll();
        return ResponseEntity.ok(productList.stream().map(ProductMapper.INSTANCE::toDto).toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(ProductMapper.INSTANCE.toDto(productService.getById(id)));
    }

    @GetMapping(path = "/query")
    public ResponseEntity<List<ProductDto>> getProductQuery(@RequestParam(name = "value") String value) {
        List<Product> productList = productService.getProductQuery(value);
        return ResponseEntity.ok(productList.stream().map(ProductMapper.INSTANCE::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult) {
        log.info("Creating Product : {}", productDto);
        if (bindingResult.hasErrors())
            throw new ResponseMessageException("401-01", "Error creating product.", CadenaUtil.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);
        Product product = ProductMapper.INSTANCE.toEntity(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.INSTANCE.toDto(productService.save(product, productDto.getIdProductType())));
    }
}
