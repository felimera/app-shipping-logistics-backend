package com.project.appshippinglogistics.repository;

import com.project.appshippinglogistics.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, ProductCriteriaRepository {

    @Query("select p from Product p where p.name like %:name%")
    List<Product> getProductList(String name);
}
