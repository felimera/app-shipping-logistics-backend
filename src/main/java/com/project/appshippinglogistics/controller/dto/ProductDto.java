package com.project.appshippinglogistics.controller.dto;

import com.project.appshippinglogistics.util.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Integer id;
    @NotEmpty(message = "The product name cannot be empty.")
    @NotNull(message = "The product name cannot be null.")
    @Size(max = 100, message = "The product's name only has a maximum of 100 characters.")
    private String name;
    @NotNull(message = "The price of the product cannot be null.")
    private Integer price;
    @NotNull(message = "The quantity of products cannot be null.")
    private Integer amount;
    @NotNull(message = "The product registration date cannot be null.")
    @Pattern(regexp = Constants.REGULARPHRASE_DATE, message = "Incorrect date format. Ex: yyyy-MM-dd.")
    private String  registrationDate;
    @NotNull(message = "The product type cannot be null.")
    private Integer idProductType;
}
