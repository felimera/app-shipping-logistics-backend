package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.PortDto;
import com.project.appshippinglogistics.exception.ResponseMessageException;
import com.project.appshippinglogistics.mapper.PortMapper;
import com.project.appshippinglogistics.model.Port;
import com.project.appshippinglogistics.service.PortService;
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
@RequestMapping(path = "/api/port")
@CrossOrigin(origins = "http://localhost:4200")
public class PortController {

    private PortService portService;

    @Autowired
    public PortController(PortService portService) {
        this.portService = portService;
    }

    @GetMapping
    public ResponseEntity<List<PortDto>> getAll() {
        List<Port> portList = portService.getAll();
        return ResponseEntity.ok(portList.stream().map(PortMapper.INSTANCE::toDto).toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PortDto> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(PortMapper.INSTANCE.toDto(portService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<Object> savePort(@Valid @RequestBody PortDto portDto, BindingResult bindingResult) {
        log.info("Creating Port: {}", portDto);
        if (bindingResult.hasErrors())
            throw new ResponseMessageException("401-01", "Error creating store.", CadenaUtil.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);
        Port port = PortMapper.INSTANCE.toEntity(portDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(PortMapper.INSTANCE.toDto(portService.save(port)));
    }
}
