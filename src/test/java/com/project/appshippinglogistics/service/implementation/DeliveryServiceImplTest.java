package com.project.appshippinglogistics.service.implementation;

import com.project.appshippinglogistics.exception.BusinessException;
import com.project.appshippinglogistics.model.Delivery;
import com.project.appshippinglogistics.model.entitybuilder.DeliveryBuilder;
import com.project.appshippinglogistics.repository.*;
import com.project.appshippinglogistics.service.CustomerService;
import com.project.appshippinglogistics.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceImplTest {
    @Mock
    private DeliveryRepository deliveryRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private ProductService productService;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private PortRepository portRepository;
    @Mock
    private ShipRepository shipRepository;
    @InjectMocks
    private DeliveryServiceImpl deliveryServiceImpl;
    Delivery delivery;

    @BeforeEach
    void setUp() {
        delivery = DeliveryBuilder.builder().build().toDelivery();
    }

    @DisplayName("Test JUnit for the GetAll method.")
    @Test
    void whenTheShippingQueryReturnsList() {
        Delivery delivery1 = DeliveryBuilder.builder().build().toEditId(2);
        given(deliveryRepository.findAll()).willReturn(List.of(delivery, delivery1));
        List<Delivery> deliveryList = deliveryServiceImpl.getAll();
        assertThat(deliveryList).isNotNull();
        assertThat(deliveryList.size()).isEqualTo(2);
    }

    @DisplayName("Test JUnit for query by Id.")
    @Test
    void whenHeReturnsAnDeliveryRecord() {
        given(deliveryRepository.findById(anyInt())).willReturn(Optional.of(delivery));
        Delivery entity = deliveryServiceImpl.getById(anyInt());
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isNotNull();
    }

    @DisplayName("Test JUnit for query by Id.")
    @Test
    void whenTheDeliveryQueryReturnsNothing() {
        given(deliveryRepository.findById(anyInt())).willReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> deliveryServiceImpl.getById(anyInt()));
        verify(deliveryRepository, never()).findById(1);
    }
}