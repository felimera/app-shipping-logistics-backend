package com.project.appshippinglogistics.mapper;

import com.project.appshippinglogistics.controller.dto.CustomerDto;
import com.project.appshippinglogistics.controller.dto.SignUpDto;
import com.project.appshippinglogistics.model.Customer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto toDto(Customer customer);

    @InheritInverseConfiguration
    Customer toEntity(CustomerDto customerDto);

    Customer toSignUpDto(SignUpDto dto);
}
