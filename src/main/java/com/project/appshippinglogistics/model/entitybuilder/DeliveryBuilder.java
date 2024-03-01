package com.project.appshippinglogistics.model.entitybuilder;

import com.project.appshippinglogistics.model.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DeliveryBuilder {
    private Integer id;
    private String guideNumber;
    private Integer price;
    private Integer amount;
    private Double discount;
    private LocalDate deadline;

    private Customer customer;
    private Product product;
    private Store store;
    private Vehicle vehicle;
    private Port port;
    private Ship ship;

    private DeliveryBuilder toDeliveryBuilder() {
        return DeliveryBuilder.builder()
                .id(1)
                .guideNumber("asd1254b")
                .price(12000)
                .amount(120)
                .discount(11.0)
                .deadline(LocalDate.now())
                .customer(null)
                .product(null)
                .store(null)
                .vehicle(null)
                .port(null)
                .ship(null)
                .build();
    }

    public Delivery toDelivery() {
        DeliveryBuilder builder = toDeliveryBuilder();
        return new Delivery(
                builder.id,
                builder.guideNumber,
                builder.price,
                builder.amount,
                builder.discount,
                builder.deadline,
                builder.customer,
                builder.product,
                builder.store,
                builder.vehicle,
                builder.port,
                builder.ship
        );
    }
    public Delivery toEditId(Integer id) {
        DeliveryBuilder builder = toDeliveryBuilder();
        return new Delivery(
                id,
                builder.guideNumber,
                builder.price,
                builder.amount,
                builder.discount,
                builder.deadline,
                builder.customer,
                builder.product,
                builder.store,
                builder.vehicle,
                builder.port,
                builder.ship
        );
    }
}
