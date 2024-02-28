package com.project.appshippinglogistics.mapper;

import com.project.appshippinglogistics.controller.dto.StoreDto;
import com.project.appshippinglogistics.model.Store;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    StoreDto toDto(Store entity);

    @InheritInverseConfiguration
    Store toEntity(StoreDto dto);
}
