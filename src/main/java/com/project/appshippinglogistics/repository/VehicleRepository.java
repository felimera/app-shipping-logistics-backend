package com.project.appshippinglogistics.repository;

import com.project.appshippinglogistics.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    @Query("select v from Vehicle v where v.licensePlate like %:licensePlate%")
    List<Vehicle> getVehicleList(String licensePlate);
}
