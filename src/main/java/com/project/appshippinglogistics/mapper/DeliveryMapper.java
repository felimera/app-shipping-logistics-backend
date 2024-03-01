package com.project.appshippinglogistics.mapper;

import com.project.appshippinglogistics.controller.dto.DeliveryDto;
import com.project.appshippinglogistics.model.Delivery;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryMapper {

    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

    DeliveryDto toDto(Delivery entity);

    @InheritInverseConfiguration
    Delivery toEntity(DeliveryDto dto);

}
