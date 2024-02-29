package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.VehicleDto;
import com.project.appshippinglogistics.exception.ResponseMessageException;
import com.project.appshippinglogistics.mapper.VehicleMapper;
import com.project.appshippinglogistics.model.Vehicle;
import com.project.appshippinglogistics.service.VehicleService;
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
@RequestMapping(path = "/api/vehicle")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {

    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getAll() {
        List<Vehicle> vehicleList = vehicleService.getAll();
        return ResponseEntity.ok(vehicleList.stream().map(VehicleMapper.INSTANCE::toDto).toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VehicleDto> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(VehicleMapper.INSTANCE.toDto(vehicleService.getById(id)));
    }

    @GetMapping(path = "/query")
    public ResponseEntity<List<VehicleDto>> getStoreQuery(@RequestParam(name = "value") String value) {
        List<Vehicle> vehicleList = vehicleService.getStoreQuery(value);
        return ResponseEntity.ok(vehicleList.stream().map(VehicleMapper.INSTANCE::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<Object> saveVehicle(@Valid @RequestBody VehicleDto vehicleDto, BindingResult bindingResult) {
        log.info("Creating Vehicle: {}", vehicleDto);
        if (bindingResult.hasErrors())
            throw new ResponseMessageException("401-01", "Error creating store.", CadenaUtil.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);
        Vehicle vehicle = VehicleMapper.INSTANCE.toEntity(vehicleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(VehicleMapper.INSTANCE.toDto(vehicleService.save(vehicle)));
    }
}
