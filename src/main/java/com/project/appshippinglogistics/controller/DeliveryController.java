package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.DeliveryDto;
import com.project.appshippinglogistics.controller.dto.search.DeliverySeek;
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

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/delivery")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping(path = "/query/priceall")
    public ResponseEntity<List<Integer>> getAllPrice() {
        return ResponseEntity.ok(deliveryService.getAllPrice());
    }

    @GetMapping(path = "/query/amountall")
    public ResponseEntity<List<Integer>> getAllAmount() {
        return ResponseEntity.ok(deliveryService.getAllAmount());
    }

    @GetMapping(path = "/query/discountall")
    public ResponseEntity<List<Integer>> getAllDiscount() {
        return ResponseEntity.ok(deliveryService.getAllDiscount());
    }

    @GetMapping(path = "/query")
    public ResponseEntity<List<DeliveryDto>> getDeliveryForMultiParameter(
            @RequestParam(name = "guideNumber", required = false) String guideNumber,
            @RequestParam(name = "price", required = false) Integer price,
            @RequestParam(name = "amount", required = false) Integer amount,
            @RequestParam(name = "discount", required = false) Integer discount,
            @RequestParam(name = "initialDeadline", required = false) LocalDate initialDeadline,
            @RequestParam(name = "finalDeadline", required = false) LocalDate finalDeadline,
            @RequestParam(name = "idCustomer", required = false) Integer idCustomer,
            @RequestParam(name = "idProduct", required = false) Integer idProduct,
            @RequestParam(name = "idStore", required = false) Integer idStore,
            @RequestParam(name = "idVehicle", required = false) Integer idVehicle,
            @RequestParam(name = "idPort", required = false) Integer idPort,
            @RequestParam(name = "idShip", required = false) Integer idShip
    ) {
        DeliverySeek deliverySeek = new DeliverySeek();
        deliverySeek.setGuideNumber(guideNumber);
        deliverySeek.setPrice(price);
        deliverySeek.setAmount(amount);
        deliverySeek.setDiscount(discount);
        deliverySeek.setInitialDeadline(initialDeadline);
        deliverySeek.setFinalDeadline(finalDeadline);
        deliverySeek.setIdCustomer(idCustomer);
        deliverySeek.setIdProduct(idProduct);
        deliverySeek.setIdStore(idStore);
        deliverySeek.setIdVehicle(idVehicle);
        deliverySeek.setIdPort(idPort);
        deliverySeek.setIdShip(idShip);
        List<Delivery> deliveryList = deliveryService.getDeliveryForMultiParameter(deliverySeek);
        return ResponseEntity.ok(deliveryList.stream().map(DeliveryMapper.INSTANCE::toDto).toList());
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
