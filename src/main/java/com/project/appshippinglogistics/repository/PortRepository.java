package com.project.appshippinglogistics.repository;

import com.project.appshippinglogistics.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortRepository extends JpaRepository<Port, Integer> {

    @Query("select p from Port p where p.location like %:location%")
    List<Port> getPortList(String location);
}
