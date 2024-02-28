package com.project.appshippinglogistics.mapper;

import com.project.appshippinglogistics.controller.dto.PortDto;
import com.project.appshippinglogistics.model.Port;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PortMapper {
    PortMapper INSTANCE = Mappers.getMapper(PortMapper.class);

    PortDto toDto(Port entity);

    @InheritInverseConfiguration
    Port toEntity(PortDto dto);
}
