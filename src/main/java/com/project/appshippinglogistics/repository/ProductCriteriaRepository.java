package com.project.appshippinglogistics.repository;

import com.project.appshippinglogistics.model.Product;
import com.project.appshippinglogistics.model.search.ProductSeeker;

import java.util.List;

public interface ProductCriteriaRepository {
    List<Product> getConsultProductForVariousParameters(ProductSeeker seeker);
}
