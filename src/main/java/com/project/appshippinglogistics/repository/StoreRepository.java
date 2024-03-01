package com.project.appshippinglogistics.repository;

import com.project.appshippinglogistics.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    @Query("select s from Store s where s.address like %:address%")
    List<Store>getStoreList(String address);
}
