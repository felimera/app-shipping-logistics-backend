package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.StoreDto;
import com.project.appshippinglogistics.exception.ResponseMessageException;
import com.project.appshippinglogistics.mapper.StoreMapper;
import com.project.appshippinglogistics.model.Store;
import com.project.appshippinglogistics.service.StoreService;
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
@RequestMapping(path = "/api/store")
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAll() {
        List<Store> storeList = storeService.getAll();
        return ResponseEntity.ok(storeList.stream().map(StoreMapper.INSTANCE::toDto).toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StoreDto> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(StoreMapper.INSTANCE.toDto(storeService.getById(id)));
    }

    @GetMapping(path = "/query")
    public ResponseEntity<List<StoreDto>> getStoreQuery(@RequestParam(name = "value") String value) {
        List<Store> storeList = storeService.getStoreQuery(value);
        return ResponseEntity.ok(storeList.stream().map(StoreMapper.INSTANCE::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<Object> saveStore(@Valid @RequestBody StoreDto storeDto, BindingResult bindingResult) {
        log.info("Creating Store: {}", storeDto);
        if (bindingResult.hasErrors())
            throw new ResponseMessageException("401-01", "Error creating store.", CadenaUtil.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);
        Store store = StoreMapper.INSTANCE.toEntity(storeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(StoreMapper.INSTANCE.toDto(storeService.save(store)));
    }
}
