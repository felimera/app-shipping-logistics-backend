package com.project.appshippinglogistics.mapper;

import com.project.appshippinglogistics.controller.dto.ProductDto;
import com.project.appshippinglogistics.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(Product entity);

    @InheritInverseConfiguration
    Product toEntity(ProductDto dto);
}
