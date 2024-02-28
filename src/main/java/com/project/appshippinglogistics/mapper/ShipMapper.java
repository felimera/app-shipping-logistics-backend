package com.project.appshippinglogistics.mapper;

import com.project.appshippinglogistics.controller.dto.ShipDto;
import com.project.appshippinglogistics.model.Ship;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShipMapper {
    ShipMapper INSTANCE = Mappers.getMapper(ShipMapper.class);

    ShipDto toDto(Ship entity);

    @InheritInverseConfiguration
    Ship toEntity(ShipDto dto);
}
