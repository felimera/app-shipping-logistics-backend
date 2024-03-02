package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.ProductDto;
import com.project.appshippinglogistics.exception.ResponseMessageException;
import com.project.appshippinglogistics.mapper.ProductMapper;
import com.project.appshippinglogistics.model.Product;
import com.project.appshippinglogistics.model.search.ProductSeeker;
import com.project.appshippinglogistics.service.ProductService;
import com.project.appshippinglogistics.util.CadenaUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping(path = "/anyfilter")
    public ResponseEntity<List<ProductDto>> getConsultProductForVariousParameters(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "price", required = false) Integer price,
            @RequestParam(name = "startAmount", required = false) Integer startAmount,
            @RequestParam(name = "finalAmount", required = false) Integer finalAmount,
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "finalDate", required = false) LocalDate finalDate,
            @RequestParam(name = "idProductType", required = false) Integer idProductType
    ) {
        ProductSeeker seeker = new ProductSeeker();
        seeker.setName(name);
        seeker.setPrice(price);
        seeker.setStartAmount(startAmount);
        seeker.setFinalAmount(finalAmount);
        seeker.setStartDate(startDate);
        seeker.setFinalDate(finalDate);
        seeker.setIdProductType(idProductType);

        List<Product> productList = productService.getConsultProductForVariousParameters(seeker);
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
