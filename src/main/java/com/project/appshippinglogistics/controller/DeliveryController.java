package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.DeliveryDto;
import com.project.appshippinglogistics.exception.ResponseMessageException;
import com.project.appshippinglogistics.mapper.DeliveryMapper;
import com.project.appshippinglogistics.model.Delivery;
import com.project.appshippinglogistics.service.DeliveryService;
import com.project.appshippinglogistics.util.CadenaUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/delivery")
public class DeliveryController {

    private DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryDto>> getAll() {
        List<Delivery> deliveryList = deliveryService.getAll();
        return ResponseEntity.ok(deliveryList.stream().map(DeliveryMapper.INSTANCE::toDto).toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DeliveryDto> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(DeliveryMapper.INSTANCE.toDto(deliveryService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<Object> savePort(@Valid @RequestBody DeliveryDto deliveryDto, BindingResult bindingResult) {
        log.info("Creating Delivery: {}", deliveryDto);
        if (bindingResult.hasErrors())
            throw new ResponseMessageException("401-01", "Error creating store.", CadenaUtil.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);
        Delivery delivery = DeliveryMapper.INSTANCE.toEntity(deliveryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(DeliveryMapper.INSTANCE.toDto(deliveryService.save(delivery, deliveryDto)));
    }
}
