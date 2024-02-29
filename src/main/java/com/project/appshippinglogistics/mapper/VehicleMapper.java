package com.project.appshippinglogistics.mapper;

import com.project.appshippinglogistics.controller.dto.VehicleDto;
import com.project.appshippinglogistics.model.Vehicle;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    VehicleDto toDto(Vehicle entity);

    @InheritInverseConfiguration
    Vehicle toEntity(VehicleDto dto);
}
