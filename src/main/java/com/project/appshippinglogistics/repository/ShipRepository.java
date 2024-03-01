package com.project.appshippinglogistics.repository;

import com.project.appshippinglogistics.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Integer> {

    @Query("select s from Ship s where s.fleetNumber like %:fleetNumber%")
    List<Ship> getShipList(String fleetNumber);
}
