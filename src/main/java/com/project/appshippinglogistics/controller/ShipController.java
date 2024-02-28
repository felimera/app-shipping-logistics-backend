package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.ShipDto;
import com.project.appshippinglogistics.exception.ResponseMessageException;
import com.project.appshippinglogistics.mapper.ShipMapper;
import com.project.appshippinglogistics.model.Ship;
import com.project.appshippinglogistics.service.ShipService;
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
@RequestMapping(path = "/api/ship")
public class ShipController {

    private ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping
    public ResponseEntity<List<ShipDto>> getAll() {
        List<Ship> shipList = shipService.getAll();
        return ResponseEntity.ok(shipList.stream().map(ShipMapper.INSTANCE::toDto).toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ShipDto> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(ShipMapper.INSTANCE.toDto(shipService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<Object> saveShip(@Valid @RequestBody ShipDto shipDto, BindingResult bindingResult) {
        log.info("Creating Ship: {}", shipDto);
        if (bindingResult.hasErrors())
            throw new ResponseMessageException("401-01", "Error creating store.", CadenaUtil.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);
        Ship ship = ShipMapper.INSTANCE.toEntity(shipDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ShipMapper.INSTANCE.toDto(shipService.save(ship)));
    }
}
