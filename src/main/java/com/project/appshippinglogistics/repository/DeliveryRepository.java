package com.project.appshippinglogistics.repository;

import com.project.appshippinglogistics.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer>, DeliveryCriteriaRepository {
    @Query("select DISTINCT(d.price) from Delivery d  order by d.price asc")
    List<Integer> getPriceAll();

    @Query("select DISTINCT(d.amount) from Delivery d  order by d.amount asc")
    List<Integer> getAmountAll();

    @Query("select DISTINCT(d.discount) from Delivery d  order by d.discount asc")
    List<Integer> getDiscountAll();
}
