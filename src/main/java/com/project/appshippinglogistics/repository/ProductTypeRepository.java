package com.project.appshippinglogistics.repository;

import com.project.appshippinglogistics.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {

    @Query("select pt from ProductType pt where pt.name like %:name%")
    List<ProductType> getProductTypeList(String name);
}
