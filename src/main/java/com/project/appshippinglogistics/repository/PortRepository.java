package com.project.appshippinglogistics.repository;

import com.project.appshippinglogistics.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository extends JpaRepository<Port,Integer> {
}
