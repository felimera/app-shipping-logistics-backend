package com.project.appshippinglogistics.mapper;

import com.project.appshippinglogistics.controller.dto.ProductTypeDto;
import com.project.appshippinglogistics.model.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductTypeMapper {
    ProductTypeMapper INSTANCE = Mappers.getMapper(ProductTypeMapper.class);

    ProductTypeDto toDto(ProductType entity);
}
