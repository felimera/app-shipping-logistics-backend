package com.project.appshippinglogistics.repository.implementation;

import com.project.appshippinglogistics.controller.dto.search.DeliverySeek;
import com.project.appshippinglogistics.model.*;
import com.project.appshippinglogistics.repository.DeliveryCriteriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeliveryCriteriaRepositoryImpl implements DeliveryCriteriaRepository {

    private EntityManager em;

    @Autowired
    public DeliveryCriteriaRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Delivery> getDeliveryForMultiParameter(DeliverySeek seek) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Delivery> cq = cb.createQuery(Delivery.class);

        Root<Delivery> deliveryRoot = cq.from(Delivery.class);
        List<Predicate> predicates = new ArrayList<>();

        Join<Customer, Delivery> customerDeliveryJoin = deliveryRoot.join(Delivery_.CUSTOMER, JoinType.INNER);
        Join<Product, Delivery> productDeliveryJoin = deliveryRoot.join(Delivery_.PRODUCT, JoinType.INNER);

        if (Objects.nonNull(seek.getGuideNumber()) && !seek.getGuideNumber().isEmpty())
            predicates.add(cb.like(deliveryRoot.get(Delivery_.GUIDE_NUMBER), "%" + seek.getGuideNumber() + "%"));
        if (Objects.nonNull(seek.getPrice()) && seek.getPrice() > 0)
            predicates.add(cb.like(deliveryRoot.get(Delivery_.PRICE), "%" + seek.getPrice() + "%"));
        if (Objects.nonNull(seek.getAmount()) && seek.getAmount() > 0)
            predicates.add(cb.like(deliveryRoot.get(Delivery_.AMOUNT), "%" + seek.getAmount() + "%"));
        if (Objects.nonNull(seek.getDiscount()) && seek.getDiscount() > 0)
            predicates.add(cb.like(deliveryRoot.get(Delivery_.DISCOUNT), "%" + seek.getDiscount() + "%"));
        if (Objects.nonNull(seek.getInitialDeadline()) && Objects.nonNull(seek.getFinalDeadline())) {
            predicates.add(cb.between(deliveryRoot.get(Delivery_.DEADLINE), seek.getInitialDeadline(), seek.getFinalDeadline()));
        }
        if (Objects.nonNull(seek.getIdCustomer()) && seek.getIdCustomer() > 0)
            predicates.add(cb.equal(customerDeliveryJoin.get(Customer_.ID), seek.getIdCustomer()));
        if (Objects.nonNull(seek.getIdProduct()) && seek.getIdProduct() > 0)
            predicates.add(cb.equal(productDeliveryJoin.get(Product_.ID), seek.getIdProduct()));
        if (Objects.nonNull(seek.getIdStore()) && seek.getIdStore() > 0) {
            Join<Store, Delivery> storeDeliveryJoin = deliveryRoot.join(Delivery_.STORE, JoinType.INNER);
            predicates.add(cb.equal(storeDeliveryJoin.get(Store_.ID), seek.getIdStore()));
        }
        if (Objects.nonNull(seek.getIdVehicle()) && seek.getIdVehicle() > 0) {
            Join<Vehicle, Delivery> vehicleDeliveryJoin = deliveryRoot.join(Delivery_.VEHICLE, JoinType.INNER);
            predicates.add(cb.equal(vehicleDeliveryJoin.get(Vehicle_.ID), seek.getIdVehicle()));
        }
        if (Objects.nonNull(seek.getIdPort()) && seek.getIdPort() > 0) {
            Join<Port, Delivery> portDeliveryJoin = deliveryRoot.join(Delivery_.PORT, JoinType.INNER);
            predicates.add(cb.equal(portDeliveryJoin.get(Port_.ID), seek.getIdPort()));
        }
        if (Objects.nonNull(seek.getIdShip()) && seek.getIdShip() > 0) {
            Join<Ship, Delivery> shipDeliveryJoin = deliveryRoot.join(Delivery_.SHIP, JoinType.INNER);
            predicates.add(cb.equal(shipDeliveryJoin.get(Ship_.ID), seek.getIdShip()));
        }

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
            cq.orderBy(cb.asc(deliveryRoot.get(Delivery_.DEADLINE)));
            return em.createQuery(cq).getResultList();
        }

        return new ArrayList<>();
    }
}
